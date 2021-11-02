package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.ManagerSimpleDao;
import com.atsun.coreapi.dao.RoleSimpleDao;
import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.enums.ManagerType;
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

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author SH
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class ManagerServiceImpl implements ManagerService {

    private PermissionService permissionService;

    private RolePermissionService rolePermissionService;

    private RoleService roleService;

    private ManagerRoleService managerRoleService;

    private ManagerRoleSimpleDao managerRoleSimpleDao;

    private ManagerSimpleDao managerSimpleDao;

    private RoleSimpleDao roleSimpleDao;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }


    @Autowired
    public void setManagerRoleSimpleDao(ManagerRoleSimpleDao managerRoleSimpleDao) {
        this.managerRoleSimpleDao = managerRoleSimpleDao;
    }

    @Autowired
    public void setManagerSimpleDao(ManagerSimpleDao managerSimpleDao) {
        this.managerSimpleDao = managerSimpleDao;
    }

    @Autowired
    public void setRoleSimpleDao(RoleSimpleDao roleSimpleDao) {
        this.roleSimpleDao = roleSimpleDao;
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
    public ManagerVO getUser(String username) {
        return managerSimpleDao.getUserSql(username);
    }

    @Override
    public List<ManagerVO> getAllManager(Page page) {
        return managerSimpleDao.getAll(page);
    }

    @Override
    public Boolean addManager(ManagerDTO managerDTO) {

        //查询用户名是否存在
        String name = managerSimpleDao.getName(managerDTO.getUsername());
        if (!StringUtils.isBlank(name)) {
            log.error("=========用户名存在==========");
            return false;
        }

        // 设置用户信息
        Manager manager = new Manager();

        manager.setUsername(managerDTO.getUsername());
        manager.setRealName(managerDTO.getRealName());
        manager.setPassword(managerDTO.getPassword());
        manager.setState(managerDTO.getState());
        manager.setType(managerDTO.getType());
        manager.setCreateDatetime(new Date());
        manager.setUpdateDatetime(new Date());

        Manager m = managerSimpleDao.save(manager);
        // 根据id查询用户角色信息
        List<String> listId = managerDTO.getListId();
        List<Role> listRole = roleSimpleDao.getListRole(listId);

        //向角色和用户关联表插入数据
        for (Role r : listRole) {
            ManagerRole managerRole = new ManagerRole();
            managerRole.setManager(m);
            managerRole.setRole(r);
            //创建-跟新时间
            managerRole.setCreateDatetime(new Date());
            managerRole.setUpdateDatetime(new Date());

            managerRoleSimpleDao.save(managerRole);
        }
        log.info("===========用户数据添加成功=========");
        return true;
    }

    @Override
    public boolean deleteManager(String id) {

        // 判断用户id是否存在
        Manager manager = managerSimpleDao.getById(id);
        if (manager == null) {
            log.error("=========用户id不存在==========");
            return false;
        }
        // 删除操作
        managerRoleSimpleDao.deleteManagerId(id);
        managerSimpleDao.deleteById(id);

        log.info("========删除成功=======");
        return true;
    }

    @Override
    public String login(String username, String password) {

        // 获取用户数据
        ManagerVO manager = this.getUser(username);

        if (manager == null) {
            log.info("用户名不存在");
        }
        if (!manager.getPassword().equals(password)) {
            log.info("密码错误");
        }
        // 生成token
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.createToken(manager);

        log.info("=======token:" + token + "=========");
        return token;
    }

    @Override
    public Boolean update(ManagerDTO managerDTO) {

        //查询用户是存在
        Manager manager = managerSimpleDao.getById(managerDTO.getId());
        if (manager == null) {

            log.error("用户不存在");
            return false;
        }
        // 设置跟新参数
        manager.setState(managerDTO.getState());
        manager.setUsername(manager.getUsername());
        manager.setPassword(managerDTO.getPassword());
        manager.setRealName(managerDTO.getRealName());
        manager.setSexual(managerDTO.getSexual());
        manager.setType(managerDTO.getType());
        manager.setUpdateDatetime(new Date());

        managerSimpleDao.save(manager);
        // 根据id查询用户角色信息
        List<String> listId = managerDTO.getListId();
        List<Role> listRole = roleSimpleDao.getListRole(listId);

        // 删除用户原有的角色
        managerRoleSimpleDao.deleteManagerId(managerDTO.getId());

        // 向角色和用户关联表插入数据
        for (Role r : listRole) {
            ManagerRole managerRole = new ManagerRole();
            managerRole.setManager(manager);
            managerRole.setRole(r);
            //创建-跟新时间
            managerRole.setCreateDatetime(new Date());
            managerRole.setUpdateDatetime(new Date());

            managerRoleSimpleDao.save(managerRole);
        }

        log.info("========修改成功=========");
        return true;
    }

    @Override
    public List<ManagerVO> conditionSelect(ManagerDTO managerDTO) {

        List<ManagerVO> manager = managerSimpleDao.getPageManager(managerDTO);

        return manager;
    }

    @Override
    public AuthorizationInfo authorizationInfo(String id) {
        // 查询角色信息
        List<String> listRole = managerRoleService.getRoleIds(id);
        Set<String> listName = roleService.listName(listRole);
        // 查询授权信息
        // 根据角色id查询权限id
        List<String> listPermission = rolePermissionService.getPermissionIds(listRole);
        // 根据权限id查询sn
        Set<String> listSn = permissionService.getListSn(listPermission);
        //
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(listName);
        info.setStringPermissions(listSn);
        return info;
    }

}
