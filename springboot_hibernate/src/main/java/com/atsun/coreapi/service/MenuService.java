package com.atsun.coreapi.service;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.MenuDTO;
import com.atsun.coreapi.dto.MenuPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.vo.MenuVO;

import java.util.List;

/**
 * @author HP
 */
public interface MenuService {

    /**
     * 获取菜单权限的菜单信息
     *
     * @param listMenuId 菜单id集合
     * @return List<MenuVO>
     */
    List<MenuVO> getMenuList(List<String> listMenuId);

    /**
     * 获取全部菜单
     *
     * @param token 校验
     * @return MenuVO菜单
     */
    List<MenuVO> getAll(String token);

    /**
     * 菜单栏显示所有菜单数据
     *
     * @param menuPageDTO 菜单分页
     * @return MenuVO
     * @throws TransException 异常
     */
    PageBean<MenuVO> getAllMenu(MenuPageDTO menuPageDTO) throws TransException;

    /**
     * 添加-修改菜单数据
     *
     * @param menuDTO 菜单参数
     * @throws TransException 异常
     */
    void edit(MenuDTO menuDTO) throws TransException;


    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @throws TransException 异常
     */
    void delete(String menuId) throws TransException;

    /**
     * 获得上级下面的所有菜单
     *
     * @param pid 父id
     * @return List<MenuVO>
     * @throws TransException 异常
     */
    List<MenuVO> getAllSubmenu(String pid) throws TransException;
}
