package com.zl.demo.config.shiro;

import com.google.code.kaptcha.Constants;
import com.google.common.base.Objects;
import com.zl.demo.config.kaptcha.CaptchaException;
import com.zl.demo.config.kaptcha.CaptchaUsernamePasswordToken;
import com.zl.demo.entity.Permission;
import com.zl.demo.entity.Role;
import com.zl.demo.entity.RolePermission;
import com.zl.demo.entity.User;
import com.zl.demo.service.PermissionService;
import com.zl.demo.service.RolePermissionService;
import com.zl.demo.service.RoleService;
import com.zl.demo.service.UserService;
import com.zl.demo.utils.Encodes;
import com.zl.demo.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Slf4j
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    protected UserService userService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;

    private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);



    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
        String captcha = token.getCaptcha();
        String code = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if ( captcha == null || !captcha.equalsIgnoreCase(code)) {
            throw new CaptchaException("验证码错误");
        }

        User user = userService.findUserByLoginName(token.getUsername());
        if (user != null) {
            if (user.getLocked()) {
                logger.info("{}登录失败，原因：账户被锁定",user.getLoginName());
                throw new DisabledAccountException();
            }
            byte[] salt = Encodes.decodeHex(user.getSalt());
            return new SimpleAuthenticationInfo(new ShiroUser(user.getLoginName(), user.getName()),
                    user.getPassword(), ByteSource.Util.bytes(salt), getName());
        } else {
            logger.info("登录失败，原因：用户{}不存在",token.getUsername());
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        User user = userService.findUserByLoginName(shiroUser.loginName);
        List<Role> roles = roleService.getCurrentRole(StrUtils.newMap("userId", user.getId()));
        logger.info("{}正在登录，查询权限信息", user.getLoginName());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (null == roles) {
            return authorizationInfo;
        }
        for (Role role : roles) {
            // 基于Role的权限信息
            authorizationInfo.addRole(role.getName());
            List<RolePermission> list = rolePermissionService.findList(role.getId());
            for (RolePermission rolePermission : list) {
                Permission permission = permissionService.getById(rolePermission.getPermissionId());
                // 基于Permission的权限信息
                authorizationInfo.addStringPermission(permission.getName());
             //   authorizationInfo.addObjectPermission(permission);
            }
        }

        logger.info("{}正在登录，查询权限结束", authorizationInfo.getRoles().toString());
        return authorizationInfo;
    }


    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserService.HASH_ALGORITHM);
        matcher.setHashIterations(UserService.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }


    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public String loginName;
        public String name;

        public ShiroUser(String loginName, String name) {
            this.loginName = loginName;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return loginName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(loginName);
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (loginName == null) {
                if (other.loginName != null) {
                    return false;
                }
            } else if (!loginName.equals(other.loginName)) {
                return false;
            }
            return true;
        }
    }
}
