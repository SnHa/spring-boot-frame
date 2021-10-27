package com.atsun.coreapi.config;

import com.alibaba.fastjson.JSON;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.enums.TransCode;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.utils.ServletUtils;
import com.atsun.coreapi.vo.ManagerVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SH
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private ManagerService managerService;

    public class AuthRealm extends AuthorizingRealm {

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
            // 获取当前用户
            JwtToken token = (JwtToken) authenticationToken;

            //TODO: 解析token，获取真实账号

            // 根据当前用户名对数据库用户进行比对
            ManagerVO manager = managerService.getUser(token.getUsername());

            if (manager == null) {
                //没有该用户
                return null;
            }

            // 完成认证
            return new SimpleAuthenticationInfo(manager, token.getToken(), manager.getUsername());
        }

    }

    public static class JwtFilter extends BasicHttpAuthenticationFilter {

        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
            return false;
        }

        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

            try {
                getSubject(request, response).login(new JwtToken(ServletUtils.getJwtToken((HttpServletRequest) request)));
            } catch (AuthenticationException e) {
                log.error(String.format("Jwt filter on access denied error: %s", e.getMessage()));
                ServletUtils.writeJson((HttpServletResponse) response, JSON.toJSONString(
                        new NoDataResponse(false, TransCode.ACCOUNT_REQUIRE_LOGIN.getCode(), TransCode.ACCOUNT_REQUIRE_LOGIN.getMsg())));
                return false;
            }

            return true;
        }

    }

    @Bean
    public Realm realm() {
        return new AuthRealm();
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
        // ShrioFilterFactroy 拦截控制
        HashMap<String, String> filterMap = new HashMap<>();
        //配置路径过滤器 anthc表示需要登录后才能进入
        filterMap.put("/info/**", "authc");
        filterMap.put("/menu/**", "authc");

        filterMap.put("/**", "jwtFilter");

        Map<String, Filter> filters = new HashMap<>(2);

        filters.put("jwtFilter", new JwtFilter());

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManager);
        factoryBean.setFilters(filters);
        factoryBean.setFilterChainDefinitionMap(filterMap);

        return factoryBean;
    }

}
