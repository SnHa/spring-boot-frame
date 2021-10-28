package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.ManagerRoleSimpleDao;
import com.atsun.coreapi.dao.ManagerSimpleDao;
import com.atsun.coreapi.dao.RoleSimpleDao;
import com.atsun.coreapi.dto.JwtToken;
import com.atsun.coreapi.dto.LoginDTO;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.po.ManagerRole;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.atsun.coreapi.enums.TransCode.ACCOUNT_BAD_CREDENTIALS;

/**
 * @author SH
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRoleSimpleDao managerRoleSimpleDao;

    private final ManagerSimpleDao managerSimpleDao;

    private final RoleSimpleDao roleSimpleDao;

    public ManagerServiceImpl(ManagerRoleSimpleDao managerRoleSimpleDao, ManagerSimpleDao managerSimpleDao, RoleSimpleDao roleSimpleDao) {
        this.managerRoleSimpleDao = managerRoleSimpleDao;
        this.managerSimpleDao = managerSimpleDao;
        this.roleSimpleDao = roleSimpleDao;
    }


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

    @Override
    public String login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        JwtToken jwtToken = new JwtToken();

        // 获取用户数据
        Subject currentUser = SecurityUtils.getSubject();
        //用户名和密码生成token
        String token = "1111";
        jwtToken.setToken(token);
        // 判断是否完成认证
        if (!currentUser.isAuthenticated()) {
            currentUser.login(jwtToken);
            return (String) currentUser.getPrincipal();
        } else {
            return null;
        }
    }

}
