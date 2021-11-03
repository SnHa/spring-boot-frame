package com.atsun.coreapi.service;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionService {
    /**
     * 根据id组查询权限id集合
     *
     * @param listRole
     * @return
     */
    List<String> getPermissionIds(List<String> listRole);

}
