package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.PermissionMenuSimpleComplexDao;
import com.atsun.coreapi.po.PermissionMenu;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class PermissionMenuSimpleComplexDaoImpl extends ComplexDaoImpl<PermissionMenu, String> implements PermissionMenuSimpleComplexDao {

    @Override
    public List<String> getListMenuId(List<String> listTypeMenu) {

        String sql = "SELECT o.menu_id FROM t_permission_menu o WHERE o.permission_id IN ( :permissionId )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", listTypeMenu);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public Long getListMenuId(String id) {
        String sql = "SELECT COUNT(o.id) FROM t_permission_menu o WHERE o.menu_id=:menuId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("menuId", id);


        return super.getTotalBySql(sql, params);
    }

    @Override
    public int deletePermission(String id) {
        String where = "WHERE o.permission_id=:permissionId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", id);

        return super.delete(where, params);
    }

    @Override
    public List<String> getPermission(String id) {
        String sql = "SELECT o.id FROM t_permission_menu o WHERE o.permission_id=:permissionId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", id);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public int deleteMenu(String id) {
        String where = " o.menu_id=:menuId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("menuId", id);

        return super.delete(where, params);
    }

    @Override
    public List<String> getByIds(String id) {
        String sql = "SELECT o.id FROM t_permission_menu o WHERE o.menu_id=:menuId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("menuId", id);

        return super.getListBySql(sql, params, null, String.class);
    }
}
