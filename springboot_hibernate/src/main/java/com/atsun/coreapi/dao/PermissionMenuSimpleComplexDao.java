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
}
