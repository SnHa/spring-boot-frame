package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.dao.RoleSimpleDao;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.RoleService;
import com.atsun.coreapi.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    public List<RoleVO> getAll(Integer page, Integer size) {
        List<RoleVO> list = roleSimpleDao.getAll(page, size);
        return list;
    }

    @Override
    public Boolean add(RoleDTO roleDTO) {

        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setRemark(roleDTO.getRemark());
        role.setScope(roleDTO.getScope());
        role.setCreateDatetime(new Date());
        role.setUpdateDatetime(new Date());

        Role role1 = roleSimpleDao.save(role);

        if (role1 == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(RoleDTO roleDTO) {
        // 根据id查询
        Role role = roleSimpleDao.getRole(roleDTO.getId());

        role.setName(roleDTO.getName());
        role.setRemark(roleDTO.getRemark());
        role.setScope(roleDTO.getScope());
        role.setUpdateDatetime(new Date());

        // 修改数据
        Role save = roleSimpleDao.save(role);
        if (save == null) {
            return false;
        }
        return true;
    }

    @Override
    public Role query(String id) {
        return roleSimpleDao.getRole(id);
    }

    @Override
    public Boolean delete(String id) {
        // 根据角色id,角色——用户表进行删除
        int m = managerRoleSimpleDao.deleteByRoleId(id);
        if (m != 1) {
            return false;
        }
        // 根据角色id，角色-权限表进行删除
        int p = rolePermissionSimpleDao.deleteRoleId(id);
        if (p != 1) {
            return false;
        }
        // 根据id删除角色表
        roleSimpleDao.deleteById(id);
        log.info("=======删除成功=========");

        return true;
    }
}
