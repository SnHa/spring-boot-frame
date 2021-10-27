package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.ManagerRoleVO;
import com.atsun.coreapi.vo.RolePermissionVO;

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
    List<String> getListPermission(List<String> listRole);

}
