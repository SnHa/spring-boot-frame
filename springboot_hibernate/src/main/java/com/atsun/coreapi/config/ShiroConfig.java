package com.atsun.coreapi.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author SH
 */
@Configuration
public class ShiroConfig  {

    @Bean
    public Realm getRealm(){
        // Realm系统资源
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm getRealm){
        //securityManager  控制流程
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager getSecurityManager){
        // ShrioFilterFactroy 拦截控制
        HashMap<String, String> filterMap = new HashMap<>();
        //配置路径过滤器 anthc表示需要登录后才能进入
        filterMap.put("/info/**","authc");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManager);
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }
}
