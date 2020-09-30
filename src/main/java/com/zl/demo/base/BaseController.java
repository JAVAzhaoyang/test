package com.zl.demo.base;


import com.zl.demo.entity.User;
import com.zl.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController<T>{

    public static final String CURRENT_USER = "CURRENT_USER";

    public static final String CURRENT_ROLE = "CURRENT_ROLE";

    public static final String CURRENT_LOGINLOG= "CURRENT_LOGINLOG";

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserService userService;

    public String getCurrentUserName() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    public User getCurrentUser() {
        String loginName= SecurityUtils.getSubject().getPrincipal().toString();
        return userService.findUserByLoginName(loginName);
    }

}
