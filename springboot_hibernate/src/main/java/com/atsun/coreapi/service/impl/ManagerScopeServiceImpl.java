package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerScopeSimpleDao;
import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.service.ManagerScopeService;
import com.atsun.coreapi.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SH
 */
@Service
public class ManagerScopeServiceImpl implements ManagerScopeService {
    @Autowired
    private MenuSimpleDao menuSimpleDao;
    @Autowired
    private ManagerScopeSimpleDao managerScopeSimpleDao;
    @Override
    public List<PermissionVO> getMenuList(List<String> list) {
        return  menuSimpleDao.getMenuListV(list);
    }

    @Override
    public List<String> getById(String id) {
        return  managerScopeSimpleDao.getListScope(id);
    }
}
