package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.service.PermissionService;
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
    public List<String> getListTypeMenu(List<String> listPermission) {
        return permissionSimpleDao.getListTypeMenu(listPermission);
    }

}
