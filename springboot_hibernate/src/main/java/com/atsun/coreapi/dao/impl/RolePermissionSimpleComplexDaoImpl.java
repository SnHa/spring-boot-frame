package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.RolePermissionSimpleComplexDao;
import com.atsun.coreapi.po.RolePermission;
import com.atsun.coreapi.vo.ManagerRoleVO;
import com.atsun.coreapi.vo.RolePermissionVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author HP
 */
public class RolePermissionSimpleComplexDaoImpl extends ComplexDaoImpl<RolePermission,String> implements RolePermissionSimpleComplexDao {
    @Override
    public List<RolePermissionVO> getListPermission(List<ManagerRoleVO> listRole) {
        StringBuffer sql=new StringBuffer("SELECT o.permission_id AS permissionId FROM t_role_permission o WHERE o.role_id=:role ");
        HashMap<String, Object> params = new HashMap<>();
        params.put("role",listRole.get(0).getRoleId());
        for (int i=1;i<listRole.size();i++){
            sql.append(" OR o.role_id=:role"+i+" ");
            params.put("role"+i,listRole.get(i).getRoleId());
        }
        return super.getListBySql(sql.toString(),params,null,RolePermissionVO.class);
    }
}
