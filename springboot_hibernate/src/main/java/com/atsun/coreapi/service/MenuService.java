package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.MenuVO;

import java.util.List;

/**
 * @author HP
 */
public interface MenuService {

    /**
     * 获取菜单权限的菜单信息
     *
     * @param permissionIds
     * @return
     */
    List<MenuVO> getAll(List<String> permissionIds);

    List<MenuVO> getTree();

}
