package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.vo.RoleVO;

import java.util.List;

/**
 * @author SH
 */
public interface RoleComplexDao extends ComplexDao<Role, String> {

    /**
     * 获取多个角色信息
     *
     * @param listId
     * @return
     */
    List<Role> getListRole(List<String> listId);

    /**
     * 获取角色名字
     *
     * @param listRole
     * @return
     */
    List<String> getListName(List<String> listRole);

    /**
     * 查询全部的角色
     *
     * @param page
     * @param size
     * @return
     */
    List<RoleVO> getAll(Integer page, Integer size);

    /**
     * 根据id查询 角色
     * @param id
     * @return
     */
    Role getRole(String id);
}
