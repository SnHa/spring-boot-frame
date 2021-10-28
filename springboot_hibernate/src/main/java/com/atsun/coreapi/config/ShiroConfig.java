package com.atsun.coreapi.config;

import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author SH
 */
@Configuration
public class ShiroConfig  {

    @Autowired
    private ManagerService managerService;

    public class MyRealm extends AuthorizingRealm {

        @Override
        public  boolean supports(AuthenticationToken token){
            return token instanceof JwtToken;
        }

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            // 授权
            Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
            System.out.println(primaryPrincipal);
            return null;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            // 认证
            // 获取token
            JwtToken jwtToken= (JwtToken) authenticationToken;
            //解析token
            String password="123456";
            ManagerVO manager = managerService.getUser("sunhao");
            if (manager == null) {
                //没有该用户
                throw  new UnknownAccountException("账户不存在");
            }
            if (!manager.getPassword().equals(password)){
                throw  new IncorrectCredentialsException("密码错误");
            }
            if (!manager.getState().equals("NORMAL")){
                throw  new LockedAccountException("账户锁定");
            }
            //存入token
//
            // 完成认证
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(jwtToken.getPrincipal(),jwtToken.getCredentials(), "MyRealm");
            return info;
        }

    }

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
        filterMap.put("/menu/**","authc");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManager);
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }
}
