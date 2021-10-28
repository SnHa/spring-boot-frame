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

    @Autowired
    private ManagerRoleService managerRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMenuService permissionMenuService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private MenuSimpleDao menuSimpleDao;

    @Override
    public List<MenuVO> getMenuList(List<String> listMenuId) {
        return menuSimpleDao.getAll(listMenuId);
    }

    @Override
    public List<MenuVO> getAll(String id) {
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
