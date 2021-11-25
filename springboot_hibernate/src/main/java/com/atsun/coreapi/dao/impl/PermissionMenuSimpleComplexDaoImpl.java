package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.PermissionMenuSimpleComplexDao;
import com.atsun.coreapi.po.PermissionMenu;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
@Repository
public class PermissionMenuSimpleComplexDaoImpl extends ComplexDaoImpl<PermissionMenu, String> implements PermissionMenuSimpleComplexDao {

    @Override
    public List<String> getListMenuId(List<String> listTypeMenu) {

        String sql = "SELECT o.menu_id FROM t_permission_menu o WHERE o.permission_id IN ( :permissionId )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", listTypeMenu);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public void deletePermission(String permissionId) {

        String where = "WHERE o.permission_id=:permissionId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", permissionId);

        super.delete(where, params);
    }

    @Override
    public List<String> getPermission(String permissionId) {

        String sql = "SELECT o.id FROM t_permission_menu o WHERE o.permission_id=:permissionId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId", permissionId);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public void deleteMenu(String menuId) {

        String where = " o.menu_id=:menuId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("menuId", menuId);

        super.delete(where, params);
    }

    @Override
    public List<String> getByIds(String menuId) {
        String sql = "SELECT o.id FROM t_permission_menu o WHERE o.menu_id=:menuId";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("menuId", menuId);

        return super.getListBySql(sql, params, null, String.class);
    }
}
