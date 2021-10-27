package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.ManagerRole;

import java.util.List;

/**
 * @author HP
 */
public interface ManagerRoleComplexDao extends ComplexDao<ManagerRole, String> {

    /**
     * 查询角色id
     *
     * @param managerId
     * @return
     */
    List<String> getRoleIds(String managerId);

}
