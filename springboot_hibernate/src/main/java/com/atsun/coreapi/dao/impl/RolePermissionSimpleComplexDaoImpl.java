package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.RolePermissionSimpleComplexDao;
import com.atsun.coreapi.po.RolePermission;

import java.util.HashMap;
import java.util.List;

/**
 * @author HP
 */
public class RolePermissionSimpleComplexDaoImpl extends ComplexDaoImpl<RolePermission, String> implements RolePermissionSimpleComplexDao {

    @Override
    public List<String> getListPermission(List<String> listRole) {

        //StringBuffer sql = new StringBuffer("SELECT o.permission_id AS permissionId FROM t_role_permission o WHERE o.role_id IN ( :role) ");
         String sql="SELECT o.permission_id AS permissionId FROM t_role_permission o WHERE o.role_id IN ( :role) ";
        HashMap<String, Object> params = new HashMap<>();
        params.put("role", listRole);
        return super.getListBySql(sql,params,null,String.class);
    }

}
