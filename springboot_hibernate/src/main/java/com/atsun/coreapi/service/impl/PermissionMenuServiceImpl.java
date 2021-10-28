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
    @Autowired
    private PermissionMenuSimpleDao permissionMenuSimpleDao;

    @Override
    public List<String> getListMenuId(List<String> listTypeMenu) {

        return permissionMenuSimpleDao.getListMenuId(listTypeMenu);
    }

}