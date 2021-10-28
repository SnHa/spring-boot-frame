package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.controller.data.AccountLoginData;
import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.dto.LoginDTO;
import com.atsun.coreapi.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.atsun.coreapi.enums.TransCode.ACCOUNT_BAD_CREDENTIALS;
import static com.atsun.coreapi.enums.TransCode.ACCOUNT_LOGIN_EXCEPTION;

/**
 * @author SH
 */
@Slf4j
@Api("用户登录")
@RestController
@RequestMapping("/admin")
public class LoginController extends BaseController {

    @Autowired
    private ManagerService managerService;

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public DataResponse<AccountLoginData> login(@RequestParam("username") String username,
                                                @RequestParam("password") String password) {
        String token = managerService.login(username, password);
        AccountLoginData accountLoginData = new AccountLoginData(token);
        accountLoginData.setToken(token);
        return ok(accountLoginData);
    }

    @ApiOperation("登出")
    @DeleteMapping("out")
    public Object out() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ok();
    }
}
