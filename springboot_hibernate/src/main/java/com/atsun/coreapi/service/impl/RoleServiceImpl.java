package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.dao.RoleSimpleDao;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.dto.RolePageDTO;
import com.atsun.coreapi.enums.TransCode;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.po.RolePermission;
import com.atsun.coreapi.service.RoleService;
import com.atsun.coreapi.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author SH
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl implements RoleService {

    private RoleSimpleDao roleSimpleDao;
    private ManagerRoleSimpleDao managerRoleSimpleDao;
    private RolePermissionSimpleDao rolePermissionSimpleDao;
    private PermissionSimpleDao permissionSimpleDao;

    @Autowired
    public void setPermissionSimpleDao(PermissionSimpleDao permissionSimpleDao) {
        this.permissionSimpleDao = permissionSimpleDao;
    }

    @Autowired
    public void setRolePermissionSimpleDao(RolePermissionSimpleDao rolePermissionSimpleDao) {
        this.rolePermissionSimpleDao = rolePermissionSimpleDao;
    }

    @Autowired
    public void setManagerRoleSimpleDao(ManagerRoleSimpleDao managerRoleSimpleDao) {
        this.managerRoleSimpleDao = managerRoleSimpleDao;
    }

    @Autowired
    public void setRoleSimpleDao(RoleSimpleDao roleSimpleDao) {
        this.roleSimpleDao = roleSimpleDao;
    }

    @Override
    public Set<String> listName(List<String> roleIds) {

        List<String> listName = roleSimpleDao.getListName(roleIds);

        return new HashSet<>(listName);
    }

    @Override
    public PageBean<RoleVO> getAll(RolePageDTO rolePageDTO) {
        return roleSimpleDao.getAll(rolePageDTO.getPage(), rolePageDTO.getName());
    }

    @Override
    public void edit(RoleDTO roleDTO) throws TransException {

        Role role;

        if (StringUtils.isNotBlank(roleDTO.getId())) {

            Optional<Role> o = roleSimpleDao.findById(roleDTO.getId());

            if (!o.isPresent()) {
                throw new TransException(TransCode.RECORD_NOT_EXIST);
            }

            role = o.get();
        } else {
            role = new Role();
        }

        role.setName(roleDTO.getName());
        role.setRemark(roleDTO.getRemark());
        role.setScope(roleDTO.getScope());

        roleSimpleDao.save(role);
    }

    @Override
    public Role query(String roleId) {
        return roleSimpleDao.getRole(roleId);
    }

    @Override
    public void delete(String roleId) {

        managerRoleSimpleDao.deleteByRoleId(roleId);
        rolePermissionSimpleDao.deleteRoleId(roleId);

        roleSimpleDao.deleteById(roleId);
    }

    @Override
    public void addPermission(RoleDTO roleDTO) {

        Role role = roleSimpleDao.getById(roleDTO.getId());
        List<Permission> permissions = permissionSimpleDao.findAllById(roleDTO.getPermissionIds());

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole(role);

        for (Permission p : permissions) {
            rolePermission.setPermission(p);
            rolePermissionSimpleDao.save(rolePermission);
        }
    }

}
