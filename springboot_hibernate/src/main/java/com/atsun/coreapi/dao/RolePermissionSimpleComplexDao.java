package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.RolePermission;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionSimpleComplexDao extends ComplexDao<RolePermission, String> {

    List<String> getListPermission(List<String> listRole);

}
