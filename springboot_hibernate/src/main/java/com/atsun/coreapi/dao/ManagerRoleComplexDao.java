package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.ManagerRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HP
 */
@Repository
public interface ManagerRoleComplexDao extends ComplexDao<ManagerRole, String> {

    /**
     * 查询角色id
     *
     * @param managerId 用户id
     * @return List<String>
     */
    List<String> getRoleIds(String managerId);

    /**
     * 根据用户id删除数据
     *
     * @param managerId 用户id
     */
    void deleteByManagerId(String managerId);

    /**
     * 根据角色id进行删除
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(String roleId);
}
