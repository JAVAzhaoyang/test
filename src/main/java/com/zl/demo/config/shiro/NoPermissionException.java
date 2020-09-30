package com.zl.demo.config.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NoPermissionException {

    /**
     * @ControllerAdvice可以全局帮我们处理异常。 @ExceptionHandler当有异常产生的时候，会自己跑到这个方法中
     * @param ex
     * @return
     */

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "无操作权限";
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "权限认证失败";
    }
}
