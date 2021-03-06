package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dao.PermissionMenuSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.dto.PermissionDTO;
import com.atsun.coreapi.dto.PermissionPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.po.PermissionMenu;
import com.atsun.coreapi.service.PermissionService;
import com.atsun.coreapi.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.atsun.coreapi.enums.TransCode.CUSTOM_EXCEPTION_MSG;

/**
 * @author HP
 */
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionSimpleDao permissionSimpleDao;
    private RolePermissionSimpleDao rolePermissionSimpleDao;
    private PermissionMenuSimpleDao permissionMenuSimpleDao;
    private MenuSimpleDao menuSimpleDao;

    @Autowired
    public void setRolePermissionSimpleDao(RolePermissionSimpleDao rolePermissionSimpleDao) {
        this.rolePermissionSimpleDao = rolePermissionSimpleDao;
    }

    @Autowired
    public void setPermissionMenuSimpleDao(PermissionMenuSimpleDao permissionMenuSimpleDao) {
        this.permissionMenuSimpleDao = permissionMenuSimpleDao;
    }

    @Autowired
    public void setPermissionSimpleDao(PermissionSimpleDao permissionSimpleDao) {
        this.permissionSimpleDao = permissionSimpleDao;
    }

    @Autowired
    public void setMenuSimpleDao(MenuSimpleDao menuSimpleDao) {
        this.menuSimpleDao = menuSimpleDao;
    }

    @Override
    public List<String> getListTypeMenu(List<String> listPermission) {
        return permissionSimpleDao.getListTypeMenu(listPermission);
    }

    @Override
    public Set<String> getListSn(List<String> permissionIds) {

        List<String> listSn = permissionSimpleDao.getListSn(permissionIds);

        return new HashSet<>(listSn);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(PermissionDTO permissionDTO) {

        Permission permission;

        if (StringUtils.isNotBlank(permissionDTO.getId())) {

            permission = permissionSimpleDao.getPermission(permissionDTO.getId());

            List<String> permissionId = permissionMenuSimpleDao.getPermission(permissionDTO.getId());

            if (permissionId != null) {
                permissionMenuSimpleDao.deletePermission(permissionDTO.getId());
            }
        } else {
            permission = new Permission();
        }

        permission.setOrderNum(0);
        permission.setName(permissionDTO.getName());
        permission.setRemark(permissionDTO.getRemark());
        permission.setScope(permissionDTO.getScope());
        permission.setSn(permissionDTO.getSn());
        permission.setType(permissionDTO.getType());

        if (permissionDTO.getParentPermission().getId() != null) {
            Permission parentPermission = permissionSimpleDao.getParent(permissionDTO.getParentPermission().getId());
            permission.setParentPermission(parentPermission);
        } else {
            permission.setParentPermission(null);
        }

        permissionSimpleDao.save(permission);

        for (String id : permissionDTO.getListMenuId()) {
            permissionMenuSimpleDao.save(new PermissionMenu(permission, new Menu(id)));
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String permissionId) throws TransException {

        if (StringUtils.isBlank(permissionSimpleDao.getById(permissionId).getId())) {
            throw new TransException(CUSTOM_EXCEPTION_MSG, "???????????????");
        }

        rolePermissionSimpleDao.deletePermission(permissionId);
        rolePermissionSimpleDao.flush();
        permissionMenuSimpleDao.deletePermission(permissionId);
        permissionMenuSimpleDao.flush();

        permissionSimpleDao.delById(permissionId);
    }

    @Override
    public PageBean<PermissionVO> getAll(PermissionPageDTO permissionPageDTO) {
        return permissionSimpleDao.getAll(permissionPageDTO.getPage(), permissionPageDTO.getName());
    }

    @Override
    public List<PermissionVO> getAllSubPermission(String pid) {
        return permissionSimpleDao.getByPid(pid);
    }

}
