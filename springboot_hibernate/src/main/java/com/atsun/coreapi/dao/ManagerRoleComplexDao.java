package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.ManagerRole;
import com.atsun.coreapi.vo.ManagerRoleVO;

import java.util.List;

/**
 * @author HP
 */
public interface ManagerRoleComplexDao extends ComplexDao<ManagerRole,String>{
    /**
     * 查询角色id
     * @param id
     * @return
     */
    List<ManagerRoleVO> getListRoleId(String id);
}
