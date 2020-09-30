package com.zl.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //主页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //首页面
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    //修改密码页面
    @RequestMapping("/password_update")
    public String passwordUpdate() {
        return "password_update";
    }

    //重置密码页面
    @RequestMapping("/password_reset")
    public String passwordReset(){
        return "password_reset";
    }

    //人员管理页面
    @RequestMapping("/user_info")
    public String userInfo() {
        return "user_info";
    }

    //人员权限管理页面
    @RequestMapping("/qx_manage")
    public String qxManage() {
        return "qx_manage";
    }

    @RequestMapping("/qx_manage_list")
    public String qxManageList() {
        return "qx_manage_list";
    }

    //角色权限管理页面
    @RequestMapping("/role_info")
    public String role() {
        return "role_info";
    }

    //未授权mapping配置
    @RequestMapping("/403")
    public String error(){
        return "403!";
    }


    @RequestMapping("/picc")
    public String picc(){
        return "picc";
    }

    //测试页面
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    //上传页面car
    @RequestMapping("/picc_dr")
    public String picc_dr(){
        return "picc_dr";
    }

}
