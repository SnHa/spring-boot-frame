package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.RolePermission;
import com.atsun.coreapi.vo.ManagerRoleVO;
import com.atsun.coreapi.vo.RolePermissionVO;

import java.util.List;

/**
 * @author HP
 */
public interface RolePermissionSimpleComplexDao extends ComplexDao<RolePermission, String> {

    List<RolePermissionVO> getListPermission(List<ManagerRoleVO> listRole);

}
