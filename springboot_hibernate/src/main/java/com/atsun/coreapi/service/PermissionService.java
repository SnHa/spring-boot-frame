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
     * 添加-修改权限
     *
     * @param permissionDTO
     * @throws TransException
     */
    void edit(PermissionDTO permissionDTO) throws TransException;


    /**
     * 根据id删除权限级权限相关表
     *
     * @param id 权限id
     * @throws TransException
     */
    void delete(String id) throws TransException;

    /**
     * 添加子菜单
     *
     * @param permissionDTO
     * @throws TransException
     */
    void addSon(PermissionDTO permissionDTO) throws TransException;

    /**
     * 分页获取全部的权限信息
     *
     * @param permissionPageDTO
     * @return
     * @throws TransException
     */
    PageBean<PermissionVO> getAll(PermissionPageDTO permissionPageDTO) throws TransException;
}
