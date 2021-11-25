package com.atsun.coreapi.service;

import java.util.List;

/**
 * @author HP
 */
public interface ManagerRoleService {

    /**
     * 根据id查询角色id
     *
     * @param managerId 用户id
     * @return List<String>
     */
    List<String> getRoleIds(String managerId);

}
