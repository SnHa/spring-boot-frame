package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.MenuVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author SH
 */
@Repository
public interface MenuComplexDao extends ComplexDao<Menu, String> {

    /**
     * 根据菜单id查询菜单数据
     *
     * @param listMenuId 菜单id集合
     * @return List<MenuVO>
     */
    List<MenuVO> getAll(List<String> listMenuId);


    /**
     * 删除菜单数据
     *
     * @param id 菜单id
     */
    void deleteId(String id);

    /**
     * 查询所有菜单-分页一级菜单
     *
     * @param page     页码，大小
     * @param menuName 菜单名称
     * @param title    菜单标题
     * @return 菜单分页数据
     */
    PageBean<MenuVO> getAllMenu(Page page, String menuName, String title);

    /**
     * 获取上级id下面的子菜单
     *
     * @param pid 父id
     * @return List<MenuVO>
     */
    List<MenuVO> getAllSubmenu(String pid);
}
