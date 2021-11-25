package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.PermissionMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author SH
 */
@Repository
public interface PermissionMenuSimpleComplexDao extends ComplexDao<PermissionMenu, String> {
    /**
     * 根据菜单类型的权限查询菜单的id
     *
     * @param listTypeMenu 菜单类型
     * @return 菜单权限id
     */
    List<String> getListMenuId(List<String> listTypeMenu);


    /**
     * 权限id删除数据
     *
     * @param permissionId 权限id
     */
    void deletePermission(String permissionId);

    /**
     * 根据权限id查询数据
     *
     * @param permissionId 权限id
     * @return list
     */
    List<String> getPermission(String permissionId);

    /**
     * 菜单id删除数据
     *
     * @param menuId 菜单id
     */
    void deleteMenu(String menuId);

    /**
     * 查询菜单id数量
     *
     * @param menuId 菜单id
     * @return list
     */
    List<String> getByIds(String menuId);
}
