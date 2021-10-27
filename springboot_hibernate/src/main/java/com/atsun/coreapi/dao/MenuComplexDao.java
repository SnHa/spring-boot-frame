package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;

import java.util.List;

/**
 * @author SH
 */
public interface MenuComplexDao extends ComplexDao<Menu, String> {



    List<PermissionVO> getMenuListV(List<String> list);

    List<MenuVO> getListMenu(List<String> listMenuId);
}
