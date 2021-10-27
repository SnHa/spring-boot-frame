package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.PermissionMenuSimpleComplexDao;
import com.atsun.coreapi.po.PermissionMenu;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class PermissionMenuSimpleComplexDaoImpl extends ComplexDaoImpl<PermissionMenu,String> implements PermissionMenuSimpleComplexDao {

    @Override
    public List<String> getListMenuId(List<String> listTypeMenu) {

        String sql="SELECT o.menu_id FROM t_permission_menu o WHERE o.permission_id IN ( :permissionId )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("permissionId",listTypeMenu);

        return super.getListBySql(sql,params,null,String.class);
    }
}
