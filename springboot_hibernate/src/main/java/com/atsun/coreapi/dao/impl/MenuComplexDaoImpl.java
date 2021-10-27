package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.MenuComplexDao;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class MenuComplexDaoImpl extends ComplexDaoImpl<Menu,String> implements MenuComplexDao {
    @Override
    public List<Menu> getMenuList(List<String> list) {
        StringBuffer sql=new StringBuffer("SELECT o.name AS name FROM t_menu o where o.scope=:scope ");
        HashMap<String, Object>  params= new HashMap<>();
        params.put("scope",list.get(0));
        //遍历范围
        for (int i=1;i<list.size();i++) {
            sql.append("OR o.scope=:scope"+i+" ");
            params.put("scope"+i,list.get(i));
        }
        return super.getListBySql(sql.toString(),params,null);
    }

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
    public String getMenuPath(String name) {
        String sql="SELECT o.path FROM t_menu o WHERE o.name LIKE :name";
        HashMap<String, Object> params = new HashMap<>();
        params.put("name",name);
       return super.getSingleResultBySql(sql,params, String.class);
    }
}
