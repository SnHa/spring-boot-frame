package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.enums.PermissionType;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 */
public interface PermissionComplexDao extends ComplexDao<Permission, String> {

    /**
     * 查询集合权限id中是菜单类型的权限id
     *
     * @param listPermission
     * @return
     */
    List<String> getListTypeMenu(List<String> listPermission);

    /**
     * 根据id查询sn
     *
     * @param listPermission
     * @return
     */
    List<String> getListSn(List<String> listPermission);

    /**
     * 获取父节点信息
     *
     * @param id
     * @return
     */
    Permission getParent(String id);

    /**
     * 根据id获取全部信息
     *
     * @param id
     * @return
     */
    Permission getPermission(String id);


    /**
     * 获取全部的权限信息
     *
     * @param page
     * @param name
     * @return
     */
    PageBean<PermissionVO> getAll(Page page, String name);

    /**
     * 根据父id查询子权限
     *
     * @param perIds
     * @return
     */
    List<PermissionVO> getByPid(ArrayList<String> perIds);
}
