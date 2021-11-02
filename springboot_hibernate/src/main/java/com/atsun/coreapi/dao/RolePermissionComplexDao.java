package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.RolePermission;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionComplexDao extends ComplexDao<RolePermission, String> {

    /**
     * 根据角色id查询权限id集合
     *
     * @param roleIds
     * @return list集合
     */
    List<String> getPermissionIds(List<String> roleIds);

    /**
     * 根据角色id删除苏剧
     *
     * @param id
     * @return
     */
    int deleteRoleId(String id);

    /**
     * 根据权限id进行删除
     *
     * @param id
     * @return
     */
    int deletePermission(String id);
}
