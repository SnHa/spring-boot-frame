package com.atsun.coreapi.service;

import com.atsun.coreapi.dto.RoleDTO;
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
     * @param page
     * @param size
     * @return
     */
    List<RoleVO> getAll(Integer page, Integer size);

    /**
     * 添加数据
     *
     * @param roleDTO
     * @return
     */
    Boolean add(RoleDTO roleDTO);

    /**
     * 修改数据
     *
     * @param roleDTO
     * @return
     */
    Boolean update(RoleDTO roleDTO);

    /**
     * 查询单个数据
     *
     * @param id
     * @return
     */
    Role query(String id);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    Boolean delete(String id);
}
