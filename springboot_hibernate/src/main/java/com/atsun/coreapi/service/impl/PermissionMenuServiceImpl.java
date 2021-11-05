package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.PermissionMenuSimpleDao;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.service.PermissionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionalException;
import java.util.List;

import static com.atsun.coreapi.enums.TransCode.CUSTOM_EXCEPTION_MSG;

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
    public void delete(String id) throws TransException {

        int m = permissionMenuSimpleDao.deleteMenu(id);
        if (m == 0) {
            throw new TransException(CUSTOM_EXCEPTION_MSG, "关联表删除失败");
        }

    }

    @Override
    public int query(String id) {
        List<String> list = permissionMenuSimpleDao.getByIds(id);
        return list.size();
    }

}
