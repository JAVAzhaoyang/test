package com.zl.demo.config.shiro;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class                                          ShiroConfig {

    @Bean
    public CacheManager simpleCacheManager() {
        return  new CacheManager();
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManager(simpleCacheManager());
        ehcacheManager.setCacheManagerConfigFile("classpath:/ehcache.xml");
        return ehcacheManager;
    }

    /**
     * tips 自定义Realm
     *
     * @parameter: EhCacheManager ehCacheManager
     * @author: hihuzi 2018/8/26 13:04
     */
    @Bean
    public ShiroDbRealm realm(EhCacheManager ehCacheManager) {
        ShiroDbRealm realm = new ShiroDbRealm();
        realm.setCacheManager(ehCacheManager);
        return realm;
    }




    /**
     * tips 安全管理器     权限管理，配置主要是Realm的管理认证
     *
     * @notice: 加入自定义的realm
     * @parameter:
     * @author: hihuzi 2018/9/10 15:31
     */
    @Bean
    public SecurityManager securityManager(ShiroDbRealm realm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * tips 拦截配置  Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @parametereter: SecurityManager securityManager
     * @return: ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        log.info("shiro权限拦截开始...");
        ShiroFilterFactoryBean shiro = new ShiroFilterFactoryBean();
        shiro.setSecurityManager(securityManager);

        //未登录跳转页面
        shiro.setLoginUrl("/login");
        //登录成功跳转页面
        shiro.setSuccessUrl("/index");
        shiro.setUnauthorizedUrl("/403");
        Map<String, Filter> filters = shiro.getFilters();
        log.info("shiro Configure 开始释放静态资源...");

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/login", "anon");
        map.put("/logout", "logout");
        map.put("/captcha/**", "anon");
        map.put("/favicon.ico", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/img/**", "anon");
        map.put("/images/**", "anon");
        map.put("/static/**", "anon");
        map.put("/**", "authc");
        shiro.setFilterChainDefinitionMap(map);
        return shiro;
    }


    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn({ "lifecycleBeanPostProcessor" })
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * tips 开启shiro aop注解支持 使用代理方式;所以需要开启代码支持;
     *
     * @parameter: securityManager
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        log.info("开启了shiro注解功能...");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * tips 标签Html用于thymeleaf模板使用shiro标签
     *
     * @author: hihuzi 2018/9/10 15:32
     */
 /*   @Bean
    public ShiroDialect shiroDialect() {

        return new ShiroDialect();
    }*/
}
