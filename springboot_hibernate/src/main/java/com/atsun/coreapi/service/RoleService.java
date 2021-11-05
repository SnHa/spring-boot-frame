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
     * @param listRole
     * @return
     */
    Set<String> listName(List<String> listRole);

    /**
     * 查询全部用户
     *
     * @param rolePageDTO
     * @return
     * @throws TransException
     */
    PageBean<RoleVO> getAll(RolePageDTO rolePageDTO) throws TransException;


    /**
     * 添加-修改数据
     *
     * @param roleDTO
     * @throws TransException
     */
    void edit(RoleDTO roleDTO) throws TransException;

    /**
     * 查询单个数据
     *
     * @param id
     * @return
     * @throws TransException
     */
    Role query(String id) throws TransException;

    /**
     * 根据id删除数据
     *
     * @param id
     * @throws TransException
     */
    void delete(String id) throws TransException;

    /**
     * 添加授权
     *
     * @param roleDTO
     */
    void addPermission(RoleDTO roleDTO) throws TransException;
}
