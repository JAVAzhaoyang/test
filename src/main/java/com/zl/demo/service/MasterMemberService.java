package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.*;
import com.zl.demo.mapper.MasterMemberMapper;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MasterMemberService extends ServiceImpl<MasterMemberMapper, MasterMember> {

    @Autowired
    private MasterMemberMapper mapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;


    /**
     * 增加权限
     */
    public int orgPermissionSet(User user,String memberId, String id, int type) {
        Role role = roleService.getRole(type);
        MasterMember masterMember = mapper.selectOne(new QueryWrapper<MasterMember>().lambda()
                .eq(MasterMember::getDeleteflag,0)
                .eq(MasterMember::getType,type)
                .eq(MasterMember::getOrgId,id)
                .eq(MasterMember::getMemberId,memberId));
        if(masterMember == null){
            String userId = user.getMemberId();
            user = userService.getOne(new QueryWrapper<User>().lambda()
                    .eq(User::getDeleteflag,0)
                    .eq(User::getMemberId,memberId));
            UserRole userRole = new UserRole();
            userRole.setRoleId(role.getId());
            userRole.setUserId(user.getId());
            userRoleService.save(userRole);

            masterMember = new MasterMember();
            masterMember.setCreatedtime(new Date());
            masterMember.setUpdatedtime(new Date());
            masterMember.setType(type);
            masterMember.setOrgId(id);
            masterMember.setMemberId(memberId);
            masterMember.setCreateId(userId);
            mapper.insert(masterMember);
            return 0;
        }else{
            return 1;
        }
    }

    /**
     * 删除权限
     */
    public int orgPermissionDelete(String memberId, String id, int type) {

        //删除 t_master_member表中的机构关联信息
        mapper.delete(new QueryWrapper<MasterMember>().lambda()
                .eq(MasterMember::getDeleteflag,0)
                .eq(MasterMember::getType,type)
                .eq(MasterMember::getOrgId,id)
                .eq(MasterMember::getMemberId,memberId));
        Role role = roleService.getRole(type);

        User user = userService.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getDeleteflag,0)
                .eq(User::getMemberId,memberId));

        //删除 t_user_role表中的权限关联信息
        if (role != null && user != null) {
            userRoleService.remove(new QueryWrapper<UserRole>().lambda()
                    .eq(UserRole::getRoleId,role.getId())
                    .eq(UserRole::getUserId,user.getId()));
            return 0;
        }else{
            return 1;

        }
    }
}
