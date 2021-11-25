package com.atsun.coreapi.service;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionService {
    /**
     * 根据id组查询权限id集合
     *
     * @param listRole 角色集合
     * @return List<String>
     */
    List<String> getPermissionIds(List<String> listRole);

}
