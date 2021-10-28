package com.atsun.coreapi.service;

import java.util.List;

/**
 * @author HP
 */
public interface PermissionService {

    /**
     * 查询满足type的id
     * @param listPermission
     * @return
     */
    List<String> getListTypeMenu(List<String> listPermission);
}
