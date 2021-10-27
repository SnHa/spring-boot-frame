package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.ManagerSimpleDao;
import com.atsun.coreapi.dao.RoleSimpleDao;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.po.ManagerRole;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author SH
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRoleSimpleDao managerRoleSimpleDao;

    @Autowired
    private ManagerSimpleDao managerSimpleDao;

    @Autowired
    private RoleSimpleDao roleSimpleDao;

    @Override
    public ManagerVO getUser(String username) {
        return managerSimpleDao.getUserSql(username);
    }

    @Override
    public List<ManagerVO> getAllManager() {
        return managerSimpleDao.getAll();
    }

    @Override
    public Manager addManager(ManagerDTO managerDTO) {

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

        // 设置用户角色信息
        Role role = new Role();
        role.setName("测试");

        Role r = roleSimpleDao.save(role);

        ManagerRole managerRole = new ManagerRole();
        managerRole.setManager(m);
        managerRole.setRole(r);

        managerRoleSimpleDao.save(managerRole);

        return null;
    }

    @Override
    public boolean deleteManager(String id) {
        try {
            managerSimpleDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
