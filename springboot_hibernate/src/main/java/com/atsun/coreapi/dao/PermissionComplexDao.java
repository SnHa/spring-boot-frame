package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HP
 */
@Repository
public interface PermissionComplexDao extends ComplexDao<Permission, String> {

    /**
     * 查询集合权限id中是菜单类型的权限id
     *
     * @param listPermission 权限id集合
     * @return List<String>
     */
    List<String> getListTypeMenu(List<String> listPermission);

    /**
     * 根据id查询sn
     *
     * @param ids 权限id集合
     * @return List<String>
     */
    List<String> getListSn(List<String> ids);

    /**
     * 获取父节点信息
     *
     * @param id 父节点id
     * @return Permission
     */
    Permission getParent(String id);

    /**
     * 根据id获取全部信息
     *
     * @param permissionId 权限id
     * @return Permission
     */
    Permission getPermission(String permissionId);


    /**
     * 获取全部的一级权限信息
     *
     * @param page page
     * @param name 权限名
     * @return PageBean<PermissionVO>
     */
    PageBean<PermissionVO> getAll(Page page, String name);

    /**
     * 根据父id查询子权限
     *
     * @param pid 父id
     * @return List<PermissionVO>
     */
    List<PermissionVO> getByPid(String pid);
}
