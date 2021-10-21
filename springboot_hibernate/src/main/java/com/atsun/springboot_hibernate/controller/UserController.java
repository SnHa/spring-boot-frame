package com.atsun.springboot_hibernate.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atsun.springboot_hibernate.entity.Role;
import com.atsun.springboot_hibernate.entity.User;
import com.atsun.springboot_hibernate.service.UserService;
import com.atsun.springboot_hibernate.util.Result;
import com.atsun.springboot_hibernate.vo.CustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author SH
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("insert")
    public Result<?> insert(@RequestBody User user){
       User userInsert = userService.insert(user);
        return Result.OK();
    }

    @ApiOperation(value = "查询用户")
    @PostMapping("select/{id}")
    public Result<?> select(@PathVariable Integer id){
        User user = userService.select(id);
        System.out.println(user.getUserName());
        Set<Role> roleSe = user.getRoleSe();
        List<String> list=new ArrayList<>();
        for (Role role:roleSe) {
            System.out.println(role.getRoleName());
            list.add(role.getRoleName());
        }

        return Result.OK(list);
    }
}
