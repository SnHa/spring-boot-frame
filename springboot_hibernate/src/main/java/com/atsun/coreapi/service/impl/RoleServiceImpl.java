package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.dao.RoleSimpleDao;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.dto.RolePageDTO;
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

import java.util.*;

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
    public Set<String> listName(List<String> listRole) {
        List<String> listName = roleSimpleDao.getListName(listRole);

        Set<String> name = new HashSet<>();

        for (String s : listName) {
            name.add(s);
        }
        return name;
    }

    @Override
    public PageBean<RoleVO> getAll(RolePageDTO rolePageDTO) {

        return roleSimpleDao.getAll(rolePageDTO.getPage(), rolePageDTO.getName());
    }

    @Override
    public void edit(RoleDTO roleDTO) throws TransException {

        Role role;

        // 判断id是否为空
        if (StringUtils.isNotBlank(roleDTO.getId())) {
            // 修改
            role = roleSimpleDao.getRole(roleDTO.getId());
            role.setUpdateDatetime(new Date());
        } else {
            //添加
            role = new Role();
        }

        role.setName(roleDTO.getName());
        role.setRemark(roleDTO.getRemark());
        role.setScope(roleDTO.getScope());

        // 修改数据
        Role save = roleSimpleDao.save(role);

    }

    @Override
    public Role query(String id) throws TransException {

        return roleSimpleDao.getRole(id);

    }

    @Override
    public void delete(String id) throws TransException {
        // 根据角色id,角色——用户表进行删除
        managerRoleSimpleDao.deleteByRoleId(id);

        // 根据角色id，角色-权限表进行删除
        rolePermissionSimpleDao.deleteRoleId(id);

        // 根据id删除角色表
        roleSimpleDao.deleteById(id);
    }

    @Override
    public void addPermission(RoleDTO roleDTO) throws TransException {

        // 查询关联表
        Role role = roleSimpleDao.getById(roleDTO.getId());
        List<Permission> permissions = permissionSimpleDao.findAllById(roleDTO.getPermissionIds());

        // 添加关联表
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole(role);
        for (Permission p : permissions) {
            rolePermission.setPermission(p);
            rolePermissionSimpleDao.save(rolePermission);
        }

    }
}
