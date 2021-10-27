package com.atsun.coreapi.controller;

import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.ManagerRoleVO;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SH
 */
@Api(tags = "菜单渲染")
@RequestMapping("/menu")
@RestController
public class MenuController {
    @Autowired
    private ManagerRoleService managerRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMenuService permissionMenuService;

    @Autowired
    private  MenuService menuService;

    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "动态获取菜单项")
    @GetMapping("select/{id}")
    public Object select(@PathVariable("id") String id){
        // 根据id查询角色id信息
        List<String> listRole=managerRoleService.getListRole(id);
        // 根据角色id查询权限id
        List<String> listPermission=rolePermissionService.getListPermission(listRole);
        // 根据权限id查询类型菜单的权限id
        List<String> listTypeMenu=permissionService.getListTypeMenu(listPermission);
        // 根据菜单类型的权限id查询菜单id
        List<String> listMenuId=permissionMenuService.getListMenuId(listTypeMenu);
        // 根据菜单id获取菜单信息
        List<MenuVO> menus=menuService.getMenuList(listMenuId);
        List<MenuVO> build = TreeUtil.build(menus);
        return new BaseController().ok(build);
    }
}
