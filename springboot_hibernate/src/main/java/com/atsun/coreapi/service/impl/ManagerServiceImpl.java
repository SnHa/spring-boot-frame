package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.ManagerSimpleDao;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.dto.ManagerPageDTO;
import com.atsun.coreapi.enums.TransCode;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Account;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.po.ManagerRole;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.utils.TokenUtils;
import com.atsun.coreapi.vo.ManagerVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author SH
 */
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRoleSimpleDao managerRoleSimpleDao;
    private ManagerSimpleDao managerSimpleDao;
    private PermissionService permissionService;
    private RolePermissionService rolePermissionService;
    private RoleService roleService;
    private ManagerRoleService managerRoleService;

    @Autowired
    public void setManagerRoleSimpleDao(ManagerRoleSimpleDao managerRoleSimpleDao) {
        this.managerRoleSimpleDao = managerRoleSimpleDao;
    }

    @Autowired
    public void setManagerSimpleDao(ManagerSimpleDao managerSimpleDao) {
        this.managerSimpleDao = managerSimpleDao;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Autowired
    public void setManagerRoleService(ManagerRoleService managerRoleService) {
        this.managerRoleService = managerRoleService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ManagerVO get(String username) {
        return managerSimpleDao.get(username);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void edit(ManagerDTO dto) throws TransException {

        //查询用户名是否存在
        String name = managerSimpleDao.getUsername(dto.getUsername(), dto.getId());

        if (!StringUtils.isBlank(name)) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "用户名已经存在");
        }

        // 设置用户信息
        Manager m;

        if (StringUtils.isNotBlank(dto.getId())) {
            Optional<Manager> o = managerSimpleDao.findById(dto.getId());
            if (!o.isPresent()) {
                throw new TransException(TransCode.RECORD_NOT_EXIST);
            }
            m = o.get();

            managerRoleSimpleDao.deleteByManagerId(dto.getId());
            managerRoleSimpleDao.flush();
        } else {
            m = new Manager();
        }

        m.setUsername(dto.getUsername());
        m.setRealName(dto.getRealName());
        m.setPassword(Account.hashPassword(dto.getPassword()));
        m.setState(dto.getState());
        m.setType(dto.getType());

        managerSimpleDao.saveAndFlush(m);

        List<ManagerRole> mrs = new ArrayList<>();

        dto.getRoleIds().forEach(roleId -> mrs.add(new ManagerRole(m, new Role(roleId))));

        if (!mrs.isEmpty()) {
            managerRoleSimpleDao.saveAll(mrs);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(String managerId) throws TransException {

        // 判断用户id是否存在
        Optional<Manager> o = managerSimpleDao.findById(managerId);

        if (!o.isPresent()) {
            throw new TransException(TransCode.RECORD_NOT_EXIST);
        }

        managerRoleSimpleDao.deleteByManagerId(managerId);
        managerSimpleDao.delById(managerId);
    }

    @Override
    public String login(String username, String password) throws TransException {

        // 获取用户数据
        ManagerVO m = get(username);

        if (null == m) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "用户名不存在");
        }

        if (Account.checkPassword(password, m.getPassword())) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "密码错误");
        }

        return new TokenUtils().createToken(m);
    }

    @Override
    public PageBean<ManagerVO> getPage(ManagerPageDTO dto) throws TransException {
        return managerSimpleDao.getPage(dto.getUsername(), dto.getState(), dto.getPage());
    }

    @Override
    public AuthorizationInfo authorizationInfo(String id) {

        // 查询角色信息
        List<String> roleIds = managerRoleService.getRoleIds(id);
        Set<String> listName = roleService.listName(roleIds);

        // 查询授权信息
        // 根据角色id查询权限id
        List<String> permissionIds = rolePermissionService.getPermissionIds(roleIds);
        // 根据权限id查询sn
        Set<String> listSn = permissionService.getListSn(permissionIds);
        Set<String> sn = new HashSet<>();

        for (String s : listSn) {
            String[] strings = s.split(",");
            sn.addAll(Arrays.asList(strings));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.setRoles(listName);
        info.setStringPermissions(sn);

        return info;
    }

}
