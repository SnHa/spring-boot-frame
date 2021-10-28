package com.atsun.coreapi.service;

import java.util.List;

/**
 * @author HP
 */
public interface ManagerRoleService {

    /**
     * 根据id查询角色id
     *
     * @param managerId
     * @return
     */
    List<String> getRoleIds(String managerId);

}
