package com.atsun.coreapi.service;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionService {
    /**
     * g根据id组查询权限id集合
     * @param listRole
     * @return
     */
    List<String> getPermissionIds(List<String> listRole);

}
