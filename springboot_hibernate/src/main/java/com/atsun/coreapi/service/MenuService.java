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
     * @param listMenuId
     * @return
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
     * @param menuPageDTO
     * @return MenuVO
     * @throws TransException
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
     * @param id
     * @throws TransException
     */
    void delete(String id) throws TransException;
}
