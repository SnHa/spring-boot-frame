package com.atsun.coreapi.controller;

import com.atsun.coreapi.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import static com.atsun.coreapi.enums.TransCode.ACCOUNT_BAD_CREDENTIALS;

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
        LoginDTO loginDTO =new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        // 获取用户数据
        Subject currentUser = SecurityUtils.getSubject();
        // 判断是否完成认证
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword());
            try {
                currentUser.login(token);
                currentUser.getSession().setAttribute("currentUser",currentUser.getPrincipal());
                return new BaseController().ok(currentUser.getSession().getAttribute("currentUser")) ;
            } catch (UnknownAccountException uae) {
                log.info("未知账户");
                return "未知账户";
            } catch (IncorrectCredentialsException ice) {
                log.info("密码不正确");
                return new BaseController().error(ACCOUNT_BAD_CREDENTIALS);
            } catch (LockedAccountException lae) {
                log.info("账户已锁定");
                return "账户已锁定";
            } catch (ExcessiveAttemptsException eae) {
                return "用户名或密码错误次数过多";
            } catch (AuthenticationException ae) {
                System.out.println("用户名或密码不正确");
                log.info("用户名或密码不正确");
                return new BaseController().error(ACCOUNT_BAD_CREDENTIALS);
            }

        }else {
            return new BaseController().error(ACCOUNT_BAD_CREDENTIALS);
        }

    }

    @ApiOperation("登出")
    @DeleteMapping("out")
    public Object out(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new BaseController().ok();
    }
}