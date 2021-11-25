package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dto.MenuDTO;
import com.atsun.coreapi.dto.MenuPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TokenUtils;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author SH
 */
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class MenuServiceImpl implements MenuService {

    private ManagerRoleService managerRoleService;
    private RolePermissionService rolePermissionService;
    private PermissionService permissionService;
    private PermissionMenuService permissionMenuService;
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
    public void setMenuSimpleDao(MenuSimpleDao menuSimpleDao) {
        this.menuSimpleDao = menuSimpleDao;
    }

    @Override
    public List<MenuVO> getMenuList(List<String> listMenuId) {
        return menuSimpleDao.getAll(listMenuId);
    }

    @Override
    public List<MenuVO> getAll(String token) {

        TokenUtils tokenUtils = new TokenUtils();
        String id = tokenUtils.validationToken(token).getId();
        log.info("用户id是： " + id);

        List<String> listRole = managerRoleService.getRoleIds(id);
        List<String> listPermission = rolePermissionService.getPermissionIds(listRole);
        List<String> listTypeMenu = permissionService.getListTypeMenu(listPermission);
        List<String> listMenuId = permissionMenuService.getListMenuId(listTypeMenu);
        List<MenuVO> menus = getMenuList(listMenuId);

        return TreeUtil.build(menus);
    }

    @Override
    public PageBean<MenuVO> getAllMenu(MenuPageDTO menuPageDTO) {
        return menuSimpleDao.getAllMenu(menuPageDTO.getPage(), menuPageDTO.getName(), menuPageDTO.getTitle());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(MenuDTO menuDTO) {
        Menu menu;

        if (StringUtils.isNotBlank(menuDTO.getId())) {
            menu = menuSimpleDao.getById(menuDTO.getId());
        } else {
            menu = new Menu();
        }

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

        if (!StringUtils.isBlank(menuDTO.getParentMenu().getId())) {
            Menu parentMenu = menuSimpleDao.getById(menuDTO.getParentMenu().getId());
            menu.setParentMenu(parentMenu);
        } else {
            menu.setParentMenu(null);
        }

        menuSimpleDao.save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String menuId) throws TransException {

        int list = permissionMenuService.query(menuId);

        if (list != 0) {
            permissionMenuService.delete(menuId);
        }

        menuSimpleDao.deleteId(menuId);
    }

    @Override
    public List<MenuVO> getAllSubmenu(String pid) {
        return menuSimpleDao.getAllSubmenu(pid);
    }

}
