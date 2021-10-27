package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.enums.PermissionType;
import com.atsun.coreapi.service.MenuService;
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

    private MenuSimpleDao menuSimpleDao;
    private RolePermissionSimpleDao rolePermissionSimpleDao;
    private ManagerRoleSimpleDao managerRoleSimpleDao;

    @Autowired
    public void setMenuSimpleDao(MenuSimpleDao menuSimpleDao) {
        this.menuSimpleDao = menuSimpleDao;
    }

    @Autowired
    public void setRolePermissionSimpleDao(RolePermissionSimpleDao rolePermissionSimpleDao) {
        this.rolePermissionSimpleDao = rolePermissionSimpleDao;
    }

    @Autowired
    public void setManagerRoleSimpleDao(ManagerRoleSimpleDao managerRoleSimpleDao) {
        this.managerRoleSimpleDao = managerRoleSimpleDao;
    }

    @Override
    public List<MenuVO> getAll(List<String> permissionIds) {
        return menuSimpleDao.getAll(permissionIds);
    }

    @Override
    public List<MenuVO> getTree() {

        // 根据id查询角色id信息
        List<String> roleIds = managerRoleSimpleDao.getRoleIds(id);
        // 根据角色id查询权限id
        List<String> permissionIds = rolePermissionSimpleDao.getPermissionIds(roleIds, PermissionType.MENU);
        // 根据菜单id获取菜单信息
        List<MenuVO> menus = menuSimpleDao.getAll(permissionIds);

        return TreeUtil.build(menus);
    }

}
