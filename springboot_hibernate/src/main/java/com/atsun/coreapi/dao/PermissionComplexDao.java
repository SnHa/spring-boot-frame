package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;

import java.util.List;

/**
 * @author HP
 */
public interface PermissionComplexDao extends ComplexDao<Permission,String> {


    List<String> getListTypeMenu(List<String> listPermission);

}
