package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.ManagerRoleComplexDao;
import com.atsun.coreapi.po.ManagerRole;
import com.atsun.coreapi.vo.ManagerRoleVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author HP
 */
public class ManagerRoleComplexDaoImpl extends ComplexDaoImpl<ManagerRole,String> implements ManagerRoleComplexDao {

    @Override
    public List<String> getListRoleId(String id) {
        String sql="SELECT o.role_id AS roleId FROM t_manager_role o WHERE o.manager_id=:managerId";
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("managerId",id);
        return super.getListBySql(sql,params,null,String.class);
    }
}
