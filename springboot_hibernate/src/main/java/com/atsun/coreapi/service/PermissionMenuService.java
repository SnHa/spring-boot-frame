package com.atsun.coreapi.service;

import com.atsun.coreapi.exception.TransException;

import java.util.List;

/**
 * @author HP
 */
public interface PermissionMenuService {
    /**
     * 获取菜单id
     *
     * @param listTypeMenu
     * @return
     */
    List<String> getListMenuId(List<String> listTypeMenu);

    /**
     * 根据菜单id删除关联表数据
     *
     * @param id 菜单的id
     * @throws TransException 异常处理
     */
    void delete(String id) throws TransException;

    /**
     * 查询是否存在关联的菜单数据
     *
     * @param id
     * @return
     */
    int query(String id);

}
