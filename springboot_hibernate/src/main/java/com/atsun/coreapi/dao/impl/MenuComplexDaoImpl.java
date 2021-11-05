package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.MenuComplexDao;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.MenuVO;
import org.apache.commons.lang3.StringUtils;

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
    public int deleteId(String id) {
        String where = " WHERE o.id=:id";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id", id);

        return super.delete(where, params);
    }

    @Override
    public PageBean<MenuVO> getAllMenu(Page page, String name, String title) {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.p_id AS pid, o.component AS component, o.redirect AS redirect, o.path AS path, o.meta AS meta FROM t_menu o WHERE 1=1 ";

        HashMap<String, Object> params = new HashMap<>(5);

        if (StringUtils.isNotBlank(name)) {
            sql += " AND o.name=:name ";
            params.put("name", name);
        }

        if (StringUtils.isNotBlank(title)) {
            sql += " AND o.title=:title";
            params.put("title", title);
        }

        return super.getPageBySql(sql, params, null, page, MenuVO.class);
    }

    @Override
    public List<MenuVO> getListMenu() {
        String sql = "SELECT o.id AS id, o.name AS name, o.remark AS remark, o.scope AS scope," +
                " o.p_id AS pid, o.component AS component, o.redirect AS redirect, o.path AS path, o.meta AS meta FROM t_menu o  ";

        return getListBySql(sql, null, null, MenuVO.class);
    }

}
