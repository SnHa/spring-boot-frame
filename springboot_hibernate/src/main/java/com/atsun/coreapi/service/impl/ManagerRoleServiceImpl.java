package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.service.ManagerRoleService;
import com.atsun.coreapi.vo.ManagerRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HP
 */
@Service
public class ManagerRoleServiceImpl implements ManagerRoleService {

    @Autowired
    private ManagerRoleSimpleDao managerRoleSimpleDao;

    @Override
    public List<ManagerRoleVO> getListRole(String id) {
        return managerRoleSimpleDao.getListRoleId(id);
    }

}
