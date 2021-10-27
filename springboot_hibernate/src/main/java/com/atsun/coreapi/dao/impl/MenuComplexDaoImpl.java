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
public class MenuComplexDaoImpl extends ComplexDaoImpl<Menu,String> implements MenuComplexDao {


    @Override
    public List<PermissionVO> getMenuListV(List<String> list) {
        StringBuffer sql=new StringBuffer("SELECT o.name AS name FROM t_menu o where o.scope=:scope ");
        HashMap<String, Object>  params= new HashMap<>();
        params.put("scope",list.get(0));
        //遍历范围
        for (int i=1;i<list.size();i++) {
            sql.append("OR o.scope=:scope"+i+" ");
            params.put("scope"+i,list.get(i));
        }
        return super.getListBySql(sql.toString(),params,null, PermissionVO.class);
    }

    @Override
    public List<MenuVO> getAll(List<String> listMenuId) {

        String sql="SELECT o.id AS id, o.name AS name, o.path AS path, o.p_id AS pid, o.title AS title   FROM t_menu o WHERE o.id IN (:id )";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("id" ,listMenuId);

        return super.getListBySql(sql,params,null,MenuVO.class);
    }

}
