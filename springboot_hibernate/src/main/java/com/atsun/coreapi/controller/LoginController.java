package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.controller.data.AccountLoginData;
import com.atsun.coreapi.dto.LoginDTO;
import com.atsun.coreapi.enums.TransCode;
import com.atsun.coreapi.exception.TransException;
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
public class LoginController extends BaseController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public DataResponse<AccountLoginData> login(@RequestParam("username") String username,
                                                @RequestParam("password") String password
    ) throws TransException {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername(username);
        loginDTO.setPassword(password);

        // 获取用户数据
        Subject currentUser = SecurityUtils.getSubject();

        // 判断是否完成认证
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword());
            try {
                currentUser.login(token);

                //TODO: 生成jwt Token

                return ok(new AccountLoginData(null));
            } catch (UnknownAccountException uae) {
                log.info("未知账户");
                throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "未知账户");
            } catch (IncorrectCredentialsException ice) {
                log.info("密码不正确");
            } catch (LockedAccountException lae) {
                log.info("账户已锁定");
            } catch (ExcessiveAttemptsException eae) {
                return "用户名或密码错误次数过多";
            } catch (AuthenticationException ae) {
                System.out.println("用户名或密码不正确");
                log.info("用户名或密码不正确");
                return error(ACCOUNT_BAD_CREDENTIALS);
            }
        } else {
            return error(ACCOUNT_BAD_CREDENTIALS);
        }

    }

    @ApiOperation("登出")
    @DeleteMapping("/logout")
    public NoDataResponse logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ok();
    }

}
