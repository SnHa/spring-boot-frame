package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.controller.data.AccountLoginData;
import com.atsun.coreapi.enums.TransCode;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author SH
 */
@Slf4j
@Api("用户登录")
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private ManagerService managerService;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public DataResponse<AccountLoginData> login(@RequestParam("username") String username,
                                                @RequestParam("password") String password
    ) throws TransException {

        String token = managerService.login(username, password);

        if (StringUtils.isBlank(token)) {
            throw new TransException(TransCode.SYSTEM_ERROR);
        }

        return ok(new AccountLoginData(token));
    }

    @ApiOperation("登出")
    @DeleteMapping("/out")
    public Object out() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ok();
    }

}
