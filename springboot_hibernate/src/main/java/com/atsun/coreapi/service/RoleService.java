package com.atsun.coreapi.service;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.dto.RolePageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.vo.RoleVO;

import java.util.List;
import java.util.Set;

/**
 * @author SH
 */
public interface RoleService {
    /**
     * 查询角色名称
     *
     * @param listRole 角色
     * @return Set<String>
     */
    Set<String> listName(List<String> listRole);

    /**
     * 查询全部用户
     *
     * @param rolePageDTO 角色分页
     * @return PageBean<RoleVO>
     * @throws TransException 异常
     */
    PageBean<RoleVO> getAll(RolePageDTO rolePageDTO) throws TransException;


    /**
     * 添加-修改数据
     *
     * @param roleDTO 角色
     * @throws TransException 异常
     */
    void edit(RoleDTO roleDTO) throws TransException;

    /**
     * 查询单个数据
     *
     * @param roleId 角色id
     * @return Role query
     * @throws TransException 异常
     */
    Role query(String roleId) throws TransException;

    /**
     * 根据id删除数据
     *
     * @param roleId 角色id
     * @throws TransException 异常
     */
    void delete(String roleId) throws TransException;

    /**
     * 添加授权
     *
     * @param roleDTO 角色
     * @throws TransException 异常
     */
    void addPermission(RoleDTO roleDTO) throws TransException;
}
