package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Permission;

import java.util.List;

/**
 * @author HP
 */
public interface PermissionComplexDao extends ComplexDao<Permission,String> {


    List<String> getListTypeMenu(List<String> listPermission);

}
