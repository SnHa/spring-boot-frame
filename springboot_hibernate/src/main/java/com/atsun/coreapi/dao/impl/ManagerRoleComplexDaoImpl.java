package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.ManagerRoleComplexDao;
import com.atsun.coreapi.po.ManagerRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author HP
 */
@Repository
public class ManagerRoleComplexDaoImpl extends ComplexDaoImpl<ManagerRole, String> implements ManagerRoleComplexDao {

    @Override
    public List<String> getRoleIds(String managerId) {

        String sql = "SELECT o.role_id FROM t_manager_role o WHERE o.manager_id=:managerId";

        HashMap<String, Object> params = new HashMap<>(3);
        params.put("managerId", managerId);

        return super.getListBySql(sql, params, null, String.class);
    }

}
