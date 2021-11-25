package com.atsun.coreapi.service;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.PermissionDTO;
import com.atsun.coreapi.dto.PermissionPageDTO;
import com.atsun.coreapi.exception.TransException;
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
     * @param listPermission 权限id集合
     * @return List<String>
     */
    List<String> getListTypeMenu(List<String> listPermission);

    /**
     * 根据权限id查询sn
     *
     * @param listPermission 权限id集合
     * @return Set<String>
     */
    Set<String> getListSn(List<String> listPermission);

    /**
     * 添加-修改权限
     *
     * @param permissionDTO 权限
     * @throws TransException 异常
     */
    void edit(PermissionDTO permissionDTO) throws TransException;


    /**
     * 根据id删除权限级权限相关表
     *
     * @param permissionId 权限id
     * @throws TransException 异常
     */
    void delete(String permissionId) throws TransException;

    /**
     * 分页获取全部的权限信息
     *
     * @param permissionPageDTO 权限
     * @return PageBean<PermissionVO>
     * @throws TransException 异常
     */
    PageBean<PermissionVO> getAll(PermissionPageDTO permissionPageDTO) throws TransException;

    /**
     * 查询上级id下所有权限
     *
     * @param pid 父id
     * @return List<PermissionVO>
     * @throws TransException 异常
     */
    List<PermissionVO> getAllSubPermission(String pid) throws TransException;
}
