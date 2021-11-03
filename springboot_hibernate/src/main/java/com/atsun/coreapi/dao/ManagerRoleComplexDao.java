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

    /**
     * 根据用户id删除数据
     *
     * @param managerId
     * @return
     */
    int deleteByManagerId(String managerId);

    /**
     * 根据用户id进行删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);
}
