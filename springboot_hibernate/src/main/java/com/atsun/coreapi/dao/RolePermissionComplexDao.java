package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HP
 */
@Repository
public interface RolePermissionComplexDao extends ComplexDao<RolePermission, String> {

    /**
     * 根据角色id查询权限id集合
     *
     * @param roleIds 角色id集合
     * @return list集合
     */
    List<String> getPermissionIds(List<String> roleIds);

    /**
     * 根据角色id删除苏剧
     *
     * @param roleId 角色id
     */
    void deleteRoleId(String roleId);

    /**
     * 根据权限id进行删除
     *
     * @param permissionId 权限id
     */
    void deletePermission(String permissionId);
}
