package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.ManagerScope;

import java.util.List;

/**
 * @author HP
 */
public interface ManagerScopeComplexDao extends ComplexDao<ManagerScope,String>{
    /**
     * 根据用户id查询菜单
     * @param id
     * @return
     */
    List<String> getListScope(String id);
}
