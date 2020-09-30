package com.zl.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zl.demo.base.BaseController;
import com.zl.demo.entity.Member;
import com.zl.demo.entity.Role;
import com.zl.demo.entity.RolePermission;
import com.zl.demo.service.RolePermissionService;
import com.zl.demo.service.RoleService;
import com.zl.demo.service.UserRoleService;
import com.zl.demo.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;


    /**
     * 角色权限查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/select/roleInfo")
    @ResponseBody
    public Map<Object, Object> roleInfo(@RequestParam(defaultValue = "1") int pageNumber,
                                        @RequestParam(defaultValue = "20") int pageSize){

        Page page = new Page<Role>(pageNumber, pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new QueryWrapper<Role>().lambda();
        queryWrapper.eq(Role::getDeleteflag,0);
        IPage iPage = roleService.page(page, queryWrapper);
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("total", iPage.getTotal());
        map.put("totalPages", iPage.getPages());
        map.put("currentPage", pageNumber);
        map.put("rows", iPage.getRecords());
        return map;
    }

    /**
     * 查询角色信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/view/role")
    @ResponseBody
    public Map<String, Object> roleView(@RequestParam String id) {
        Map<String, Object> map = Maps.newHashMap();
        Role role = roleService.getById(id);
        map.put("insertsign",role.getInsertsign());
        map.put("deletesign",role.getDeletesign());
        map.put("updatesign",role.getUpdatesign());
        return map;
    }


    @RequestMapping(value = "/update/role")
    @ResponseBody
    public Map<String, Object> carUpdate(@RequestParam(required = false) String id,
                                         @RequestParam(required = false) String insertsign,
                                         @RequestParam(required = false) String deletesign,
                                         @RequestParam(required = false) String updatesign){
        Map<String, Object> map = Maps.newHashMap();
        Role role = roleService.getById(id);


        // 1.判断读取的角色的权限标识与新赋予权限是否相同;
        // 2.如果不同的话,判断传值是赋予权限,还是去除权限;
        // 3.权限表Permission中  1:所有权限; 2:添加权限; 3:删除权限; 4:修改权限
        String insertId = "2";
        String deleteId = "3";
        String updateId = "4";
        if(!insertsign.equals(role.getInsertsign())){
            if(insertsign.equals("0")){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(id);
                rolePermission.setPermissionId(insertId);
                rolePermissionService.save(rolePermission);
            }else{
                rolePermissionService.remove(new QueryWrapper<RolePermission>().lambda()
                        .eq(RolePermission::getPermissionId,id).eq(RolePermission::getPermissionId,insertId));
            }
        }
        if(!deletesign.equals(role.getDeletesign())){
            if(deletesign.equals("0")){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(id);
                rolePermission.setPermissionId(deleteId);
                rolePermissionService.save(rolePermission);
            }else{
                rolePermissionService.remove(new QueryWrapper<RolePermission>().lambda()
                        .eq(RolePermission::getPermissionId,id).eq(RolePermission::getPermissionId,deleteId));
            }
        }
        if(!updatesign.equals(role.getUpdatesign())){
            if(updatesign.equals("0")){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(id);
                rolePermission.setPermissionId(updateId);
                rolePermissionService.save(rolePermission);
            }else{
                rolePermissionService.remove(new QueryWrapper<RolePermission>().lambda()
                        .eq(RolePermission::getPermissionId,id).eq(RolePermission::getPermissionId,updateId));
            }
        }

        role.setInsertsign(MathUtils.StrToInteger(insertsign));
        role.setDeletesign(MathUtils.StrToInteger(deletesign));
        role.setUpdatesign(MathUtils.StrToInteger(updatesign));
        roleService.updateById(role);
        map.put("retCode", "0");
        map.put("retMessage", "信息修改成功");
        return map;
    }

}
