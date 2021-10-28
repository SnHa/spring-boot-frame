package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SH
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final ManagerRoleService managerRoleService;

    private final RolePermissionService rolePermissionService;

    private final PermissionService permissionService;

    private final PermissionMenuService permissionMenuService;

    private final ManagerService managerService;

    private final MenuSimpleDao menuSimpleDao;

    public MenuServiceImpl(ManagerRoleService managerRoleService, RolePermissionService rolePermissionService, PermissionService permissionService, PermissionMenuService permissionMenuService, ManagerService managerService, MenuSimpleDao menuSimpleDao) {
        this.managerRoleService = managerRoleService;
        this.rolePermissionService = rolePermissionService;
        this.permissionService = permissionService;
        this.permissionMenuService = permissionMenuService;
        this.managerService = managerService;
        this.menuSimpleDao = menuSimpleDao;
    }

    @Override
    public List<MenuVO> getMenuList(List<String> listMenuId) {
        return menuSimpleDao.getListMenu(listMenuId);
    }

    @Override
    public List<MenuVO> getAll(String id) {
        // 根据id查询角色id信息
        List<String> listRole = managerRoleService.getListRole(id);
        // 根据角色id查询权限id
        List<String> listPermission = rolePermissionService.getListPermission(listRole);
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
