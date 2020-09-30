package com.zl.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.zl.demo.base.BaseController;
import com.zl.demo.entity.Member;
import com.zl.demo.entity.Org;
import com.zl.demo.entity.User;
import com.zl.demo.service.MemberService;
import com.zl.demo.service.OrgService;
import com.zl.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrgService orgService;


    /**
     * 人员管理查询
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    @RequestMapping("/select/userInfo")
    @ResponseBody
    public Map<Object, Object> userInfo(@RequestParam(defaultValue = "1") int pageNumber,
                                        @RequestParam(defaultValue = "20") int pageSize,
                                        @RequestParam(required = false) String loginName,
                                        @RequestParam(required = false) String name){

        Page page = new Page<Member>(pageNumber, pageSize);
        LambdaQueryWrapper<Member> queryWrapper = new QueryWrapper<Member>().lambda();
        queryWrapper.eq(Member::getDeleteflag,0).orderByDesc(Member::getCreatedtime);
        if(!Strings.isNullOrEmpty(loginName)){
            queryWrapper.eq(Member::getLoginName, loginName);
        }
        if(!Strings.isNullOrEmpty(name)){
            queryWrapper.eq(Member::getName, name);
        }
        IPage iPage = memberService.page(page, queryWrapper);
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("total", iPage.getTotal());
        map.put("totalPages", iPage.getPages());
        map.put("currentPage", pageNumber);
        map.put("rows", iPage.getRecords());
        return map;
    }

    /**
     * 人员添加
     * @param id
     * @param loginName
     * @param name
     * @param mobile
     * @param email
     * @param city
     * @return
     */
    @RequestMapping(value = "/insert/user")
    @ResponseBody
    public Map<String, Object> userInsert(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String comcode,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String city) {

        Map<String, Object> map = Maps.newHashMap();

        //loginName登录名是唯一的,不可重复
        Member member = memberService.selectByLoginName(loginName);
        if(member==null){
            try {
                //添加人员信息
                member = new Member();
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
                memberService.save(member);
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
                userService.register(user);
                logger.info("当前操作人: {}，添加人员，姓名：{}", getCurrentUser().getName(), user.getName());
            } catch (Exception e) {
                logger.error("添加人员出错：{}【{}】,错误原因：{}", name,e.getMessage());
                map.put("retCode", "1");
                map.put("retMessage", e.getMessage());
                return map;
            }
            map.put("retCode", "0");
            map.put("retMessage", "添加成功");
            return map;
        }else{
            map.put("retCode", "1");
            map.put("retMessage", "登录名称已存在,请勿重复添加");
            return map;
        }
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/user")
    @ResponseBody
    public int carDelete(@RequestParam String id) {
        logger.info("【当前操作人：{}】删除人员,人员ID{}", getCurrentUser().getName(), id);
        int status = memberService.memberDelete(id);
        return status;
    }

    /**
     * 页面详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/view/user")
    @ResponseBody
    public Map<String, Object> carView(@RequestParam String id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Member member = memberService.getById(id);
            logger.info("【当前操作人：{}】查看人员信息,人员ID{}", getCurrentUser().getName(), id);
            map.put("id", member.getId());
            map.put("loginName", member.getLoginName());
            map.put("name", member.getName());
            map.put("comcode", member.getComcode());
            map.put("mobile", member.getMobile());
            map.put("email", member.getEmail());
            map.put("city", member.getCity());
        } catch (Exception e) {
            logger.error("【当前操作人：{}】查看人员信息失败,人员ID{}，失败原因{}", getCurrentUser().getName(), id, e.getMessage());
            System.out.println(e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 信息修改
     * @param id
     * @param name
     * @param mobile
     * @param email
     * @param city
     * @return
     */
    @RequestMapping(value = "/update/user")
    @ResponseBody
    @RequiresPermissions("update")//权限管理;
    public Map<String, Object> carUpdate(@RequestParam(required = false) String id,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String comcode,
                                         @RequestParam(required = false) String mobile,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String city
    ) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Member member = memberService.getById(id);
            logger.info("【当前操作人: {}】编辑人员名单信息,原来的名单,姓名{}", getCurrentUser().getName(), member.getName());
            member.setName(name);
            member.setComcode(comcode);
            member.setMobile(mobile);
            member.setEmail(email);
            member.setCity(city);
            Org org = orgService.selectByComcode(comcode);
            if(org!=null){
                member.setOrgId(org.getId());
            }
            memberService.updateById(member);

            User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getMemberId,member.getId()));
            System.out.println(user);
            user.setName(name);
            if(org!=null){
                user.setOrgId(org.getId());
            }
            userService.updateById(user);
            logger.info("【当前操作人: {}】编辑人员名单信息,更新后的人员名单信息,姓名{}，代码{}", getCurrentUser().getName(), member.getName());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改人员名单信息出错：{},错误原因：{}", name, e.getMessage());
            map.put("retCode", "1");
            map.put("retMessage", e.getMessage());
            return map;
        }
        map.put("retCode", "0");
        map.put("retMessage", "信息修改成功");
        return map;
    }





}
