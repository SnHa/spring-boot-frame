package com.atsun.coreapi.service;

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
     * 根据菜单id删除数据
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 查询是否存在关联的菜单数据
     * @param id
     * @return
     */
    int query(String id);

}
