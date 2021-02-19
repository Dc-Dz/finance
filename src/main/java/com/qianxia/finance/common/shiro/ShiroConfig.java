package com.qianxia.finance.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.qianxia.finance.common.Constant;
import com.qianxia.finance.domain.Admin;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {

    // 负责拦截请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String,String> map = new LinkedHashMap<>();
        map.put("/","anon");
        map.put("/index.html","anon");
        map.put("/bootstrap/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
        map.put("/lyear/**","anon");
        map.put("/error/**","anon");
        map.put("/toregister.html/**","anon");
        map.put("/login/**","anon");
        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    // 安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm,
                                                                  @Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        List<Realm> list = new ArrayList<>();
        list.add(adminRealm);
        list.add(userRealm);

        // 关联realm
        securityManager.setRealms(list);

        return securityManager;
    }

    // 自定义的AdminRealm
    @Bean(name = "adminRealm")
    public AdminRealm adminRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return adminRealm;
    }

    // 自定义的UserRealm
    @Bean(name = "userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    // 凭证校验匹配器
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        // 1.修改凭证校验匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 2.设置加密算法
        matcher.setHashAlgorithmName("MD5");;
        // 3.设置散列次数
        matcher.setHashIterations(Constant.MD5_HASH);

        return matcher;
    }

    // 整合thymeleaf前端模板引擎
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
