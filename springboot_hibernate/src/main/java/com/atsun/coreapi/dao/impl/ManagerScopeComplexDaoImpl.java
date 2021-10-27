package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.ManagerScopeComplexDao;
import com.atsun.coreapi.dao.MenuComplexDao;
import com.atsun.coreapi.po.ManagerScope;
import com.atsun.coreapi.po.Menu;

import java.util.HashMap;
import java.util.List;

/**
 * @author HP
 */
public class ManagerScopeComplexDaoImpl extends ComplexDaoImpl<ManagerScope,String> implements ManagerScopeComplexDao{

    @Override
    public List<String> getListScope(String id) {
        String sql="SELECT o.scope FROM t_manager_scope o WHERE o.manager_id=:id";
        HashMap<String, Object> params = new HashMap<>();
        params.put("id",id);
        return super.getListBySql(sql,params,null,String.class);
    }
}
