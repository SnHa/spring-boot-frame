package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.PermissionVO;

import java.util.List;

/**
 * @author SH
 */
public interface ManagerScopeService {
    /**
     * 查询用户能获取的菜单
     *
     * @param list
     * @return
     */
    List<PermissionVO> getMenuList(List<String> list);

    /**
     * 根据id查询范围
     *
     * @param id
     * @return
     */
    List<String> getById(String id);
}
