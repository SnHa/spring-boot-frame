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
     * @param listMenuId
     * @return
     */
    List<MenuVO> getMenuList(List<String> listMenuId);

    /**
     * 获取全部菜单
     *
     * @param token
     * @return
     */
    List<MenuVO> getAll(String token);
}
