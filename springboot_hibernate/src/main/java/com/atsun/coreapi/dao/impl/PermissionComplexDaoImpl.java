package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.PermissionComplexDao;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class PermissionComplexDaoImpl extends ComplexDaoImpl<Permission,String> implements PermissionComplexDao {

    @Override
    public List<String> getListTypeMenu(List<String> listPermission) {
        String sql="SELECT o.id FROM t_permission o WHERE o.id IN (:id) AND o.type=:type";
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id",listPermission);
        params.put("type","MENU");
        return super.getListBySql(sql,params,null,String.class);
    }
}
