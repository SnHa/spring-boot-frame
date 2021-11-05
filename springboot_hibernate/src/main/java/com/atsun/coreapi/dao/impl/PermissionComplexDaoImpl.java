package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.PermissionComplexDao;
import com.atsun.coreapi.enums.PermissionType;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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
    public PageBean<PermissionVO> getAll(Page page, String name) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.p_id AS pid FROM t_permission o WHERE o.p_id IS NULL  AND 1=1";

        HashMap<String, Object> params = new HashMap<>(5);

        if (StringUtils.isNotBlank(name)) {
            sql += " AND o.name LIKE :name ";
            params.put("name", name);
        }

        return super.getPageBySql(sql, params, null, page, PermissionVO.class);
    }

    @Override
    public List<PermissionVO> getByPid(ArrayList<String> perIds) {

        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.p_id AS pid FROM t_permission o WHERE o.p_id IN (:pIds)";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("pIds", perIds);

        return super.getListBySql(sql, params, null, PermissionVO.class);
    }

}
