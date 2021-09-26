package com.atsun.demo.controller;

import com.atsun.demo.entity.User;
import com.atsun.demo.service.UserService;
import com.atsun.demo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户")
@RestController
public class loginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //判断用户是否为空
        if (user.getUsername()==null){
            return Result.fail();
        }
       int result=userService.selectByName(user);
        if (result!=0){
            return Result.ok();
        }
        return Result.fail();
    }
    @GetMapping("/test/{id}")
    public String test(
                       @PathVariable Integer id){
        if (id==0){
            return "no";
        }
        return "test";
    }
}
