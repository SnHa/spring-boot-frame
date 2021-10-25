package com.atsun.coreapi.config;

import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SH
 */

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private ManagerService managerService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 授权
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 认证
        // 获取当前用户
        UsernamePasswordToken userToken= (UsernamePasswordToken) authenticationToken;
        // 根据当前用户名对数据库用户进行比对
        ManagerVO manager=managerService.getUser(userToken.getUsername());
        manager.setTokenVer("1111");
        if (manager==null){
            //没有该用户
            return null;
        }
        // 完成认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(manager,manager.getPassword(),"MyRealm");
        return simpleAuthenticationInfo;
    }
}
