package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.List;

/**
 * @author SH
 */
public interface MenuComplexDao extends ComplexDao<Menu, String> {

    /**
     * 根据传递的范围查询菜单
     *
     * @param list
     * @return
     */
    List<Menu> getMenuList(List<String> list);

    List<PermissionVO> getMenuListV(List<String> list);

    /**
     * 通过菜单名字获取路径
     *
     * @param name
     * @return
     */
    String getMenuPath(String name);

}
