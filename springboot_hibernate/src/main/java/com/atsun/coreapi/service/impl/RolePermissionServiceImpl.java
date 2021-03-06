package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HP
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private RolePermissionSimpleDao rolePermissionSimpleDao;

    @Autowired
    public void setRolePermissionSimpleDao(RolePermissionSimpleDao rolePermissionSimpleDao) {
        this.rolePermissionSimpleDao = rolePermissionSimpleDao;
    }

    @Override
    public List<String> getPermissionIds(List<String> roleIds) {
        return rolePermissionSimpleDao.getPermissionIds(roleIds);
    }

}
