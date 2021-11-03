package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.List;

/**
 * @author SH
 */
public interface MenuComplexDao extends ComplexDao<Menu, String> {

    /**
     * 根据菜单id查询菜单数据
     *
     * @param listMenuId
     * @return
     */
    List<MenuVO> getAll(List<String> listMenuId);

    /**
     * 查询所有菜单-分页
     *
     * @param page
     * @param size
     * @return
     */
    List<MenuVO> getAllMenu(Integer page, Integer size);

    /**
     * 删除菜单数据
     *
     * @param id
     * @return
     */
    int deleteId(String id);
}
