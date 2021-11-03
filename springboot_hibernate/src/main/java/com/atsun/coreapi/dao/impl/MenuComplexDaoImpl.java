package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.dao.MenuComplexDao;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class MenuComplexDaoImpl extends ComplexDaoImpl<Menu, String> implements MenuComplexDao {

    @Override
    public List<MenuVO> getAll(List<String> listMenuId) {

        String sql = "SELECT o.id AS id, o.name AS name, o.path AS path, o.p_id AS pid, o.title AS title   FROM t_menu o WHERE o.id IN (:id )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", listMenuId);

        return super.getListBySql(sql, params, null, MenuVO.class);
    }

    @Override
    public List<MenuVO> getAllMenu(Integer page, Integer size) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.p_id AS pid, o.component AS component, o.redirect AS redirect, o.path AS path, o.meta AS meta FROM t_menu o";

        Page pag = new Page();
        pag.setPageNumber(page);
        pag.setPageSize(size);

        return super.getPageListBySql(sql, null, null, pag, MenuVO.class);
    }

    @Override
    public int deleteId(String id) {
        String where = " WHERE o.id=:id";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", id);

        return super.delete(where, params);
    }

}
