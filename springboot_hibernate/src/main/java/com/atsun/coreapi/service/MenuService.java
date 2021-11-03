package com.atsun.coreapi.service;

import com.atsun.coreapi.dto.MenuDTO;
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

    /**
     * 菜单栏显示所有菜单数据
     *
     * @param page
     * @param size
     * @return
     */
    List<MenuVO> getAllMenu(Integer page, Integer size);

    /**
     * 添加菜单数据
     *
     * @param menuDTO
     * @return
     */
    Boolean add(MenuDTO menuDTO);

    /**
     * 修改菜单
     *
     * @param menuDTO
     * @return
     */
    Boolean update(MenuDTO menuDTO);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    Boolean delete(String id);
}
