package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerSimpleDao;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SH
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerSimpleDao managerSimpleDao;
    @Override
    public ManagerVO getUser(String username) {
        return managerSimpleDao.getUserSql(username);
    }
}
