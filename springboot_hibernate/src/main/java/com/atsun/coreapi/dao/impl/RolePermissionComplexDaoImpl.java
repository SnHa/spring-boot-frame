package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.RolePermissionComplexDao;
import com.atsun.coreapi.po.RolePermission;

import java.util.HashMap;
import java.util.List;

/**
 * @author HP
 */
public class RolePermissionComplexDaoImpl extends ComplexDaoImpl<RolePermission, String> implements RolePermissionComplexDao {

    @Override
    public List<String> getPermissionIds(List<String> roleIds) {

        String sql = "SELECT o.permission_id FROM t_role_permission o WHERE o.role_id IN (:roleIds) ";

        HashMap<String, Object> params = new HashMap<>(3);

        params.put("roleIds", roleIds);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public void deleteRoleId(String roleId) {

        String sql = "WHERE o.role_id=:roleId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("roleId", roleId);

        super.delete(sql, params);
    }

    @Override
    public void deletePermission(String permissionId) {

        String sql = "WHERE o.permission_id=:permissionId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", permissionId);

        super.delete(sql, params);
    }

}
