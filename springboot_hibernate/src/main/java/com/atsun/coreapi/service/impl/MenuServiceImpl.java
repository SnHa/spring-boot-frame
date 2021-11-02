package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TokenUtils;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SH
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    private ManagerRoleService managerRoleService;

    private RolePermissionService rolePermissionService;

    private PermissionService permissionService;


    private PermissionMenuService permissionMenuService;


    private ManagerService managerService;


    private MenuSimpleDao menuSimpleDao;

    @Autowired
    public void setManagerRoleService(ManagerRoleService managerRoleService) {
        this.managerRoleService = managerRoleService;
    }

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setPermissionMenuService(PermissionMenuService permissionMenuService) {
        this.permissionMenuService = permissionMenuService;
    }

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setMenuSimpleDao(MenuSimpleDao menuSimpleDao) {
        this.menuSimpleDao = menuSimpleDao;
    }

    @Override
    public List<MenuVO> getMenuList(List<String> listMenuId) {
        return menuSimpleDao.getAll(listMenuId);
    }

    @Override
    public List<MenuVO> getAll(String token) {
        // 解析token,得到用户id
        TokenUtils tokenUtils = new TokenUtils();
        String id = tokenUtils.validationToken(token).getId();
        log.info("用户id是： " + id);
        // 根据id查询角色id信息
        List<String> listRole = managerRoleService.getRoleIds(id);
        // 根据角色id查询权限id
        List<String> listPermission = rolePermissionService.getPermissionIds(listRole);
        // 根据权限id查询类型菜单的权限id
        List<String> listTypeMenu = permissionService.getListTypeMenu(listPermission);
        // 根据菜单类型的权限id查询菜单id
        List<String> listMenuId = permissionMenuService.getListMenuId(listTypeMenu);
        // 根据菜单id获取菜单信息
        List<MenuVO> menus = getMenuList(listMenuId);
        List<MenuVO> build = TreeUtil.build(menus);
        return build;
    }
}
