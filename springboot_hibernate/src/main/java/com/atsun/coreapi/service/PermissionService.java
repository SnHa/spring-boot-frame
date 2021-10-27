package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;

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
