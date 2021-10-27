package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.service.MenuService;
import com.atsun.coreapi.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SH
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuSimpleDao menuSimpleDao;

    @Override
    public List<MenuVO> getMenuList(List<String> listMenuId) {
        return menuSimpleDao.getListMenu(listMenuId);
    }
}
