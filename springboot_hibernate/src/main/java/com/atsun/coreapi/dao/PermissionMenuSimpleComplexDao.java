package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.PermissionMenu;

import java.util.List;

/**
 * @author SH
 */
public interface PermissionMenuSimpleComplexDao extends  ComplexDao<PermissionMenu,String>{

    List<String> getListMenuId(List<String> listTypeMenu);

}
