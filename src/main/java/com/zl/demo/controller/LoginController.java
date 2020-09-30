package com.zl.demo.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.base.Strings;
import com.zl.demo.base.BaseController;
import com.zl.demo.config.kaptcha.CaptchaException;
import com.zl.demo.config.kaptcha.CaptchaUsernamePasswordToken;
import com.zl.demo.entity.LoginLog;
import com.zl.demo.entity.Role;
import com.zl.demo.entity.User;
import com.zl.demo.service.LoginLogService;
import com.zl.demo.service.RoleService;
import com.zl.demo.service.UserService;
import com.zl.demo.utils.DateUtils;
import com.zl.demo.utils.Digests;
import com.zl.demo.utils.Encodes;
import com.zl.demo.utils.StrUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zl.demo.service.UserService.HASH_INTERATIONS;


@Controller
public class LoginController extends BaseController {

    @Autowired
    private Producer captchaProducer;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginLogService loginLogService;

    //验证码
    @RequestMapping("/captcha")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");// 设置标准 HTTP/1.0 不缓存图片
        response.setContentType("image/jpeg");// 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
        String capText = captchaProducer.createText();// 为图片创建文本
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText); // 创建带有文本的图片
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // 图片数据输出
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String loginname, String pwd, Model model, HttpSession session, HttpServletRequest request) {

        if (!Strings.isNullOrEmpty(loginname) && !Strings.isNullOrEmpty(pwd)) {
            User user = userService.findUserByLoginName(loginname);
            if(user!=null && user.getPassword().equalsIgnoreCase(pwd)){
                logger.info("{}登录成功", loginname);

                //设置session值,在首页面通过session获取对应信息
                session.setAttribute(CURRENT_USER, user);
                List<Role> roles = roleService.getCurrentRole(StrUtils.newMap("userId", user.getId()));
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < roles.size(); i++) {
                    if(i<roles.size()-1){
                        stringBuilder.append(roles.get(i).getName());
                        stringBuilder.append(",");
                    }else{
                        stringBuilder.append(roles.get(i).getName());
                    }
                }
                Role role = new Role();
                role.setName(stringBuilder.toString());
                session.setAttribute(CURRENT_ROLE,role);
                LoginLog loginLog = loginLogService.getLastLoginTime(user.getId());
                //修改日期格式,重复赋值给string字段
                if(loginLog!=null){
                    loginLog.setUserId(DateUtils.formatDateTime(loginLog.getCreatedtime()));
                    session.setAttribute(CURRENT_LOGINLOG,loginLog);
                }
                loginLogService.addLoginLog(user, request);
                return "redirect:/index";
            }
        }
        return "login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String code,
                        HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(defaultValue = "false") boolean rememberMe, Model model, HttpSession session) {

        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(username, password.toCharArray(),
                rememberMe, request.getRemoteHost(), code);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException exception) {
            model.addAttribute("error", "登录失败：用户名或密码错误");
            logger.info("登录失败：账户{}不存在", username);
        } catch (LockedAccountException exception) {
            model.addAttribute("error", "失败次数太多，账号被锁定，请稍后再试");
            logger.info("登录失败：账户{}被锁定", username);
        } catch (CaptchaException exception) {
            model.addAttribute("error", "登录失败：验证码错误");
        } catch (AuthenticationException exception) {
            model.addAttribute("error", "登录失败：用户名或密码错误");
            logger.info("登录失败：账户{}密码错误", username);
        }

        if (subject.isAuthenticated()) {
            logger.info("{}登录成功", subject.getPrincipal().toString());

            //设置session值,在首页面通过session获取对应信息(用户信息,用户角色信息,用户登录信息)
            User user = getCurrentUser();
            session.setAttribute(CURRENT_USER, user);
            List<Role> roles = roleService.getCurrentRole(StrUtils.newMap("userId", user.getId()));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < roles.size(); i++) {
                if(i<roles.size()-1){
                    stringBuilder.append(roles.get(i).getName());
                    stringBuilder.append(",");
                }else{
                    stringBuilder.append(roles.get(i).getName());
                }
            }
            Role role = new Role();
            role.setName(stringBuilder.toString());
            session.setAttribute(CURRENT_ROLE,role);
            LoginLog loginLog = loginLogService.getLastLoginTime(user.getId());
            //修改日期格式,重复赋值给string字段
            if(loginLog!=null){
                loginLog.setUserId(DateUtils.formatDateTime(loginLog.getCreatedtime()));
                session.setAttribute(CURRENT_LOGINLOG,loginLog);
            }
            loginLogService.addLoginLog(user, request);
            return "redirect:/index";
        } else {
            model.addAttribute("username", username);
            token.clear();
            return "login";
        }

    }


    @RequestMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }


    /**
     * 修改密码
     * @param oldPass 原密码
     * @param newPass 新密码
     * @return
     */
    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> passwordUpdate(@RequestParam String oldPass, @RequestParam String newPass) {
        Map<String, Object> map = new HashMap<>();
        User user = getCurrentUser();
        byte[] salt = Encodes.decodeHex(user.getSalt());
        byte[] hashPassword = Digests.sha1(oldPass.getBytes(), salt, HASH_INTERATIONS);
        String pass = Encodes.encodeHex(hashPassword);
        if (pass.equals(user.getPassword())) {
            String passwrod = Encodes.encodeHex(Digests.sha1(newPass.getBytes(), salt, HASH_INTERATIONS));
            user.setPassword(passwrod);
            userService.updateById(user);
            map.put("retCode", 0);
            map.put("retMsg", "密码修改成功，请重新登录");
        } else {
            map.put("retCode", 1);
            map.put("retMsg", "原密码不正确");
        }
        return map;
    }


    /**
     * 重置密码
     * @param oldPass  用户名
     * @param newPass  用户新密码
     * @return
     */
    @RequestMapping("/user/password/update")
    @ResponseBody
    public Map<String,Object> passwordReset(@RequestParam String oldPass, @RequestParam String newPass){
        logger.info("原先用户名为:"+oldPass );
        logger.info("原先密码为:"+newPass );
        User user = userService.findUserByLoginName(oldPass);
        String getSalt = user.getSalt();
        Map<String, Object> map = new HashMap<>();
        byte[] salt = Encodes.decodeHex(getSalt);
        String passwrod = Encodes.encodeHex(Digests.sha1(newPass.getBytes(), salt, HASH_INTERATIONS));
        user.setPassword(passwrod);
        userService.updateById(user);
        logger.info("用户名为:"+oldPass );
        logger.info("密码已经重置为:"+passwrod );
        map.put("retCode", 0);
        map.put("retMsg", "密码重置成功");
        return map;
    }




    public static void main(String[] args) {
        byte[] salt = Encodes.decodeHex("22ce3f6645791710");
        String newPass = "123456";
        String passwrod = Encodes.encodeHex(Digests.sha1(newPass.getBytes(), salt, HASH_INTERATIONS));
        System.out.println(passwrod);
    }

}
