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
}
