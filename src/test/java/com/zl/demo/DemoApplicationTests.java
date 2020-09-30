package com.zl.demo;

import com.zl.demo.entity.*;
import com.zl.demo.service.*;
import com.zl.demo.utils.StrUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private LoginLogService loginLogService;


    @Test
    void test1(){
        System.out.println(loginLogService.getLastLoginTime("881e8729-f3fa-11ea-970f-54e1ad007360").getCreatedtime());
    }

    @Test
    void test2(){
        Role role = roleService.getById("06674b47-f27f-11ea-970f-54e1ad007360");
        List<RolePermission> list = rolePermissionService.findList(role.getId());
        for (RolePermission rolePermission : list) {
            Permission permission = permissionService.getById(rolePermission.getPermissionId());
            // 基于Permission的权限信息
            System.out.println(permission.getName());
        }

    }
}
