package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.dao.RoleComplexDao;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.vo.RoleVO;
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
    public List<RoleVO> getAll(Integer page, Integer size) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.create_datetime AS createDatetime, o.update_datetime AS updateDatetime FROM t_role o";

        Page pag = new Page();
        pag.setPageNumber(page);
        pag.setPageSize(size);

        return super.getPageListBySql(sql, null, null, pag, RoleVO.class);
    }

    @Override
    public Role getRole(String id) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark,  o.scope AS scope, " +
                "  o.create_datetime AS createDatetime, o.update_datetime AS updateDatetime FROM t_role o WHERE o.id=:id";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", id);

        return super.getSingleResultBySql(sql, params, Role.class);
    }
}
