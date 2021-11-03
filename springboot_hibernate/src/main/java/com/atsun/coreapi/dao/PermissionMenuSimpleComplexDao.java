package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.PermissionMenu;

import java.util.List;

/**
 * @author SH
 */
public interface PermissionMenuSimpleComplexDao extends ComplexDao<PermissionMenu, String> {
    /**
     * 根据菜单类型的权限查询菜单的id
     *
     * @param listTypeMenu
     * @return
     */
    List<String> getListMenuId(List<String> listTypeMenu);

    /**
     * 根据菜单id查询数据
     * @param id
     * @return
     */
    Long getListMenuId(String id);

    /**
     * 权限id删除数据
     * @param id
     * @return
     */
    int deletePermission(String id);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    List<String> getPermission(String id);

    /**
     * 菜单id删除数据
     * @param id
     * @return
     */
    int deleteMenu(String id);

    /**
     * 查询菜单id数量
     * @param id
     * @return
     */
    List<String> getByIds(String id);
}
