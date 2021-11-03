package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.dto.MenuDTO;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TokenUtils;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author SH
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public List<MenuVO> getAllMenu(Integer page, Integer size) {
        List<MenuVO> list = menuSimpleDao.getAllMenu(page, size);
        List<MenuVO> build = TreeUtil.build(list);
        return build;
    }

    @Override
    public Boolean add(MenuDTO menuDTO) {

        Menu menu = new Menu();

        menu.setComponent(menuDTO.getComponent());
        menu.setEnable(menuDTO.getEnable());
        menu.setMeta(menuDTO.getMeta());
        menu.setName(menuDTO.getName());
        menu.setOrderNum(menuDTO.getOrderNum());
        menu.setTitle(menuDTO.getTitle());
        menu.setPath(menuDTO.getPath());
        menu.setRedirect(menuDTO.getRedirect());
        menu.setRemark(menuDTO.getRemark());
        menu.setScope(menu.getScope());
        menu.setCreateDatetime(new Date());
        menu.setUpdateDatetime(new Date());

        // 判断添加了父菜单
        if (!StringUtils.isBlank(menuDTO.getParentMenu().getId())) {
            // 查询父菜单数据
            Menu parentMenu = menuSimpleDao.getById(menuDTO.getParentMenu().getId());
            menu.setParentMenu(parentMenu);
        } else {
            menu.setParentMenu(null);
        }
        Menu save = menuSimpleDao.save(menu);
        if (save == null) {
            log.error("======菜单添加失败=====");
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(MenuDTO menuDTO) {

        Menu menu = menuSimpleDao.getById(menuDTO.getId());

        menu.setComponent(menuDTO.getComponent());
        menu.setEnable(menuDTO.getEnable());
        menu.setMeta(menuDTO.getMeta());
        menu.setName(menuDTO.getName());
        menu.setOrderNum(menuDTO.getOrderNum());
        menu.setTitle(menuDTO.getTitle());
        menu.setPath(menuDTO.getPath());
        menu.setRedirect(menuDTO.getRedirect());
        menu.setRemark(menuDTO.getRemark());
        menu.setScope(menu.getScope());
        menu.setUpdateDatetime(new Date());

        // 判断添加了父菜单
        if (!StringUtils.isBlank(menuDTO.getParentMenu().getId())) {
            // 查询父菜单数据
            Menu parentMenu = menuSimpleDao.getById(menuDTO.getParentMenu().getId());
            menu.setParentMenu(parentMenu);
        } else {
            menu.setParentMenu(null);
        }
        Menu save = menuSimpleDao.save(menu);
        if (save == null) {
            log.error("======菜单修改失败=====");
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(String id) {

        // 判断权限--菜单是否存在关联数据
        int list = permissionMenuService.query(id);
        if (list != 0) {

            // 删除权限菜单
            Boolean pm = permissionMenuService.delete(id);
            if (!pm) {
                log.error("==========权限-菜单表删除失败=======");
                return false;
            }
        }

        // 删除菜单
        int m = menuSimpleDao.deleteId(id);
        if (m != 1) {
            log.error("============菜单删除失败============");
            return false;
        }
        return true;
    }

}
