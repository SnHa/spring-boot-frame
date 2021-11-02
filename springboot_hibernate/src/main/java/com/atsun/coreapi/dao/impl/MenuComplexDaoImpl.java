package com.atsun.coreapi.dao.impl;

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

}
