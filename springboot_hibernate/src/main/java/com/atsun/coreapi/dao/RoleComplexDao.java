package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
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
     * @param roleIds
     * @return
     */
    List<String> getListName(List<String> roleIds);

    /**
     * 查询全部的角色
     *
     * @param page
     * @param name
     * @return
     */
    PageBean<RoleVO> getAll(Page page, String name) ;

    /**
     * 根据id查询 角色
     *
     * @param roleId
     * @return
     */
    Role getRole(String roleId);
}
