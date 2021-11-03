package com.atsun.coreapi.config;

import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.utils.AuthFilter;
import com.atsun.coreapi.utils.TokenUtils;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SH
 */
@Configuration
public class ShiroConfig {

    private ManagerService managerService;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    public class MyRealm extends AuthorizingRealm {

        @Override
        public boolean supports(AuthenticationToken token) {
            return token instanceof JwtToken;
        }

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            System.out.println("======" + "授权开始" + "=====");

            // 授权
            String token = (String) principalCollection.getPrimaryPrincipal();
            // 解析token 得到用户id
            TokenUtils tokenUtils = new TokenUtils();
            String id = tokenUtils.validationToken(token).getId();

            // 查询用户的角色，授权返回AuthorizationInfo类型
            AuthorizationInfo info = managerService.authorizationInfo(id);
            return info;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            // 认证
            // 获取token
            JwtToken jwtToken = (JwtToken) authenticationToken;
            String token = jwtToken.getToken();
            //解析token
            TokenUtils tokenUtils = new TokenUtils();
            ManagerVO managerUser = tokenUtils.validationToken(token);
            ManagerVO manager = managerService.get(managerUser.getUsername());
            if (manager == null) {
                //没有该用户
                throw new UnknownAccountException("账户不存在");
            }
            if (!manager.getState().equals("NORMAL")) {
                throw new LockedAccountException("账户锁定");
            }
            // 完成认证
            return new SimpleAuthenticationInfo(jwtToken.getPrincipal(), jwtToken.getCredentials(), manager.getUsername());
        }

    }

    @Bean
    public Realm getRealm() {
        // Realm系统资源
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm getRealm) {
        //securityManager  控制流程
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager getSecurityManager) {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        //添加自定义过滤器
        filters.put("auth", new AuthFilter());
        factoryBean.setFilters(filters);

        // ShrioFilterFactroy 拦截控制
        HashMap<String, String> filterMap = new HashMap<>();
        //配置路径过滤器 anthc表示需要登录后才能进入
        filterMap.put("/info/**", "auth");
        filterMap.put("/menu/**", "auth");
        filterMap.put("/permission/**", "auth");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }

    /**
     * @param getSecurityManager
     * @return AuthorizationAttributeSourceAdvisor
     * @Title: authorizationAttributeSourceAdvisor
     * @Description:开启shiro提供的权限相关的注解
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager getSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
