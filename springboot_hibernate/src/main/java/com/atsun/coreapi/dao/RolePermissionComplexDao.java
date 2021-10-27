package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.RolePermission;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionComplexDao extends ComplexDao<RolePermission, String> {

    List<String> getPermissionIds(List<String> roleIds);

}
