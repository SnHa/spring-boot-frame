package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.PermissionComplexDao;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author SH
 */
public class PermissionComplexDaoImpl extends ComplexDaoImpl<Permission,String> implements PermissionComplexDao {
    @Override
    public List<PermissionVO> getListMenu(List<RolePermissionVO> listPermission) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuffer sql=new StringBuffer("with recursive cte as ( select * from t_permission where id =:id ");
        params.put("id",listPermission.get(0).getPermissionId());
        for (int i=1;i<listPermission.size();i++){
            sql.append(" or id=:id"+i+" ");
            params.put("id"+i,listPermission.get(i).getPermissionId());
        }
        sql.append("union all select c.* from t_permission c, cte where c.p_id = cte.id ) select o.name as name, o.id as id, " +
                "o.p_id as pid, o.type as type,  o.scope as scope from cte o order by p_id, id asc ");
        return super.getListBySql(sql.toString(),params,null,PermissionVO.class);
    }
}
