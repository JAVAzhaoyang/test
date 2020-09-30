package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.zl.demo.entity.Member;
import com.zl.demo.entity.Org;
import com.zl.demo.entity.User;
import com.zl.demo.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MemberService extends ServiceImpl<MemberMapper, Member> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;


    //根据登录名查询成员信息
    public Member selectByLoginName(String loginName){
        return mapper.selectOne(new QueryWrapper<Member>().lambda()
                .eq(Member::getLoginName,loginName)
                .eq(Member::getDeleteflag,0));
    }

    //根据姓名或人员代码查询用户信息
    public List<Member> findByNameLikeOrCode(String keyword) {
        return mapper.findByNameLikeOrCodeAndDeleteFlag("%" + keyword + "%", keyword);
    }


    public int memberDelete(String id){
        try {
            //删除成员表信息
            Member member = mapper.selectById(id);
            member.setDeleteflag(1);
            updateById(member);
            //删除登录表信息
            User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getMemberId,id));
            user.setDeleteflag(1);
            userService.save(user);
            //删除成员,机构关系表信息

            logger.info("删除人员,人员信息{}", member.getName());
            return 0;
        } catch (Exception e) {
            logger.error("删除人员失败,失败原因{}", e.getMessage());
            return 1;
        }
    }

 /*   public int insertMember(String loginName,String comcode,String name,String mobile,String email,String city){
        try {
            Member member = new Member();
            member.setCreatedtime(new Date());
            member.setUpdatedtime(new Date());
            member.setLoginName(loginName);
            member.setName(name);
            member.setMobile(mobile);
            member.setEmail(email);
            member.setCity(city);
            //根据comcode来获取归属地市
            Org org = orgService.selectByComcode(comcode);
            member.setComcode(comcode);
            if(org!=null){
                member.setOrgId(org.getId());
            }
            mapper.insert(member);
            //添加人员的登录信息
            User user = new User();
            user.setCreatedtime(new Date());
            user.setUpdatedtime(new Date());
            user.setLoginName(loginName);
            user.setName(name);
            user.setPassword("123456");
            user.setMemberId(member.getId());
            if(org!=null){
                user.setOrgId(org.getId());
            }
            userService.save(user);
            return 0;
        }catch (Exception e) {
            logger.error("增加销售公司人员失败,错误原因{}", e.getMessage());
            return 1;
        }
    }
*/

}
