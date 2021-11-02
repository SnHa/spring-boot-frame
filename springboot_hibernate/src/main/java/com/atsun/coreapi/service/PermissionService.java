package com.atsun.coreapi.service;

import com.atsun.coreapi.dto.PermissionDTO;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.List;
import java.util.Set;

/**
 * @author HP
 */
public interface PermissionService {

    /**
     * 查询满足type的id
     *
     * @param listPermission
     * @return
     */
    List<String> getListTypeMenu(List<String> listPermission);

    /**
     * 根据权限id查询sn
     *
     * @param listPermission
     * @return
     */
    Set<String> getListSn(List<String> listPermission);

    /**
     * 添加权限
     *
     * @param permissionDTO
     * @return
     */
    Boolean add(PermissionDTO permissionDTO);

    /**
     * 修改权限
     *
     * @param permissionDTO
     * @return
     */
    Boolean update(PermissionDTO permissionDTO);

    /**
     * 根据id删除权限级权限相关表
     *
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 添加子菜单
     *
     * @param permissionDTO
     * @return
     */
    Boolean addSon(PermissionDTO permissionDTO);

    /**
     * 获取全部的权限信息
     *
     * @param page
     * @param size
     * @return
     */
    List<PermissionVO> getAll(Integer page, Integer size);
}
