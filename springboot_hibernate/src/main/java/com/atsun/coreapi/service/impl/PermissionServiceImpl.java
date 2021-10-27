package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.service.PermissionService;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HP
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionSimpleDao permissionSimpleDao;
    @Autowired
    private MenuSimpleDao menuSimpleDao;
    @Override
    public List<PermissionVO> getListMenu(List<RolePermissionVO> listPermission) {
        List<PermissionVO> listMenu = permissionSimpleDao.getListMenu(listPermission);
        for (int i=0;i<listMenu.size();i++){
            PermissionVO permissionVO = listMenu.get(i);
            String name = permissionVO.getName();
            // 获取路径
          String path = menuSimpleDao.getMenuPath(name);
          permissionVO.setPath(path);
          listMenu.set(i,permissionVO);
        }
        return  listMenu;
    }
}
