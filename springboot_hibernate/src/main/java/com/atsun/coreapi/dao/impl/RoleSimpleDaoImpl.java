package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.RoleComplexDao;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.vo.RoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
@Repository
public class RoleSimpleDaoImpl extends ComplexDaoImpl<Role, String> implements RoleComplexDao {

    @Override
    public List<Role> getListRole(List<String> listId) {
        String sql = "SELECT o.id AS id, o.create_datetime AS createDatetime, o.update_datetime AS updateDatetime," +
                " o.name AS name, o.remark AS remark, o.scope AS scope  FROM t_role o  WHERE id IN (:id )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", listId);

        return super.getListBySql(sql, params, null, Role.class);
    }

    @Override
    public List<String> getListName(List<String> listRole) {
        String sql = "SELECT o.name   FROM t_role o  WHERE id IN (:id )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", listRole);

        return super.getListBySql(sql, params, null, String.class);
    }

    @Override
    public PageBean<RoleVO> getAll(Page page, String name) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.create_datetime AS createDatetime, o.update_datetime AS updateDatetime FROM t_role o WHERE 1=1 ";

        HashMap<String, Object> params = new HashMap<>(5);

        if (StringUtils.isNotBlank(name)) {
            sql += " AND name=:name ";
            params.put("name", name);
        }

        return super.getPageBySql(sql, params, null, page, RoleVO.class);
    }

    @Override
    public Role getRole(String roleId) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark,  o.scope AS scope, " +
                "  o.create_datetime AS createDatetime, o.update_datetime AS updateDatetime FROM t_role o WHERE o.id=:id";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", roleId);

        return super.getSingleResultBySql(sql, params, Role.class);
    }
}
