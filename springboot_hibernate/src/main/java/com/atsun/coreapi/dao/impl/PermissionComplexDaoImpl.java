package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.dao.PermissionComplexDao;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RoleVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class PermissionComplexDaoImpl extends ComplexDaoImpl<Permission, String> implements PermissionComplexDao {

    @Override
    public List<String> getListTypeMenu(List<String> listPermission) {

        String sql = "SELECT o.id FROM t_permission o WHERE o.id IN (:id) AND o.type=:type";

        HashMap<String, Object> params = new HashMap<>(5);

        params.put("id", listPermission);
        params.put("type", "MENU");

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public List<String> getListSn(List<String> listPermission) {
        String sql = "SELECT o.sn FROM t_permission o WHERE o.id IN (:id) ";

        HashMap<String, Object> params = new HashMap<>(5);

        params.put("id", listPermission);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public Permission getParent(String id) {

        String where = "WHERE o.id=:id";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", id);

        return super.getSingleResult(where, params);
    }

    @Override
    public Permission getPermission(String id) {
        String where = " WHERE o.id=:id";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", id);

        return super.getSingleResult(where, params);
    }

    @Override
    public List<PermissionVO> getAll(Integer page, Integer size) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.p_id AS pid FROM t_permission o";

        Page pag = new Page();
        pag.setPageNumber(page);
        pag.setPageSize(size);

        return super.getPageListBySql(sql, null, null, pag, PermissionVO.class);
    }

}
