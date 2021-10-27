package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.ManagerRoleVO;

import java.util.List;

/**
 * @author HP
 */
public interface ManagerRoleService {
    /**
     * 根据id查询角色id
     * @param id
     * @return
     */
    List<String> getListRole(String id);
}
