package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.PermissionMenuSimpleDao;
import com.atsun.coreapi.service.PermissionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SH
 */
@Service
public class PermissionMenuServiceImpl implements PermissionMenuService {

    private PermissionMenuSimpleDao permissionMenuSimpleDao;

    @Autowired
    public void setPermissionMenuSimpleDao(PermissionMenuSimpleDao permissionMenuSimpleDao) {
        this.permissionMenuSimpleDao = permissionMenuSimpleDao;
    }

    @Override
    public List<String> getListMenuId(List<String> listTypeMenu) {

        return permissionMenuSimpleDao.getListMenuId(listTypeMenu);
    }

    @Override
    public Boolean delete(String id) {

        int m = permissionMenuSimpleDao.deleteMenu(id);
        if (m != 1) {
            return false;
        }
        return true;
    }

    @Override
    public int query(String id) {
      List<String> list =permissionMenuSimpleDao.getByIds(id);
        return list.size();
    }

}
