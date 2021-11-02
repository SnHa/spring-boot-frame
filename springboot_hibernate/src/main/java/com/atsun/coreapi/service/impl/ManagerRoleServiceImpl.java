package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.service.ManagerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HP
 */
@Service
public class ManagerRoleServiceImpl implements ManagerRoleService {

    private ManagerRoleSimpleDao managerRoleSimpleDao;

    @Autowired
    public void setManagerRoleSimpleDao(ManagerRoleSimpleDao managerRoleSimpleDao) {
        this.managerRoleSimpleDao = managerRoleSimpleDao;
    }

    @Override
    public List<String> getRoleIds(String managerId) {
        return managerRoleSimpleDao.getRoleIds(managerId);
    }

}
