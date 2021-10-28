package com.atsun.coreapi.controller;

import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.ManagerRoleVO;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SH
 */
@Slf4j
@Api(tags = "菜单渲染")
@RequestMapping("/menu")
@RestController
public class MenuController  extends BaseController{

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "动态获取菜单项")
    @GetMapping("/select/{id}")
    public Object select(@PathVariable("id") String id) {

        List<MenuVO>  build= menuService.getAll(id);
        return ok(build);
    }
}
