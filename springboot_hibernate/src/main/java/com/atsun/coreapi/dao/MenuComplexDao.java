package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.MenuVO;

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
     * 删除菜单数据
     *
     * @param id
     * @return
     */
    int deleteId(String id);

    /**
     * 查询所有菜单-分页
     *
     * @param page  页码，大小
     * @param name  菜单名称
     * @param title 菜单标题
     * @return 菜单分页数据
     */
    PageBean<MenuVO> getAllMenu(Page page, String name, String title);

    /**
     * 查询所有数据
     * @return
     */
    List<MenuVO> getListMenu();

}
