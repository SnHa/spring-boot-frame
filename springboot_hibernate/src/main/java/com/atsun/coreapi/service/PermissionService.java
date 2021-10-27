package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;

import java.util.List;

/**
 * @author HP
 */
public interface PermissionService {
    /**
     * 查询菜单
     * @param listPermission
     * @return
     */
    List<PermissionVO> getListMenu(List<RolePermissionVO> listPermission);

}
