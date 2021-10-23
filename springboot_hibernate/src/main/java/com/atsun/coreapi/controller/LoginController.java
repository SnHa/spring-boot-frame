package com.atsun.coreapi.controller;

import com.atsun.coreapi.dt.LoginDT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author SH
 */
@Slf4j
@Api("用户登录")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @ApiOperation("登录")
    @PostMapping("login")
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        LoginDT loginDT=new LoginDT();
        loginDT.setUsername(username);
        loginDT.setPassword(password);
        // 获取用户数据
        Subject currentUser = SecurityUtils.getSubject();
        // 判断是否完成认证
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(loginDT.getUsername(),loginDT.getPassword());
            try {
                currentUser.login(token);
                currentUser.getSession().setAttribute("currentUser",currentUser.getPrincipal());
                return currentUser.getSession().getAttribute("currentUser");
            } catch (UnknownAccountException uae) {
                log.info("未知账户");
                return "未知账户";
            } catch (IncorrectCredentialsException ice) {
                log.info("密码不正确");
                return "密码不正确";
            } catch (LockedAccountException lae) {
                log.info("账户已锁定");
                return "账户已锁定";
            } catch (ExcessiveAttemptsException eae) {
                return "用户名或密码错误次数过多";
            } catch (AuthenticationException ae) {
                System.out.println("用户名或密码不正确");
                log.info("用户名或密码不正确");
                return "用户名或密码不正确！";
            }

        }else {
            return null;
        }

    }

    @ApiOperation("登出")
    @GetMapping("out")
    public Object out(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "";
    }
}
