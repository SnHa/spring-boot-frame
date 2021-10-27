package com.atsun.coreapi.controller;

import com.atsun.coreapi.service.ManagerRoleService;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.service.PermissionService;
import com.atsun.coreapi.service.RolePermissionService;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.ManagerRoleVO;
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
    private ManagerService managerService;
    @ApiOperation(value = "动态获取菜单项")
    @GetMapping("select/{id}")
    public Object select(@PathVariable("id") String id){
        //根据id查询角色id信息
       List<ManagerRoleVO> listRole=managerRoleService.getListRole(id);
       //根据角色id查询权限id
       List<RolePermissionVO> listPermission=rolePermissionService.getListPermission(listRole);
        //根据权限id查询菜单
        List<PermissionVO> menuVoList=permissionService.getListMenu(listPermission);
        List<PermissionVO> build = TreeUtil.build(menuVoList);

        return new BaseController().ok(build);
    }
}
