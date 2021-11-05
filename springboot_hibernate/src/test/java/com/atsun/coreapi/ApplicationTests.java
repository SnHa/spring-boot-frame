package com.atsun.coreapi;

import com.atsun.coreapi.dao.*;
import com.atsun.coreapi.dto.ManagerPageDTO;
import com.atsun.coreapi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;

@Slf4j
@SpringBootTest
class ApplicationTests {
    @Autowired
    private ManagerRoleService managerRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleSimpleDao roleSimpleDao;
    @Autowired
    private MenuSimpleDao menuSimpleDao;
    @Autowired
    private ManagerSimpleDao managerSimpleDao;
    @Autowired
    private ManagerScopeSimpleDao managerScopeSimpleDao;
    @Autowired
    private ManagerRoleSimpleDao managerRoleSimpleDao;
    @Autowired
    private RolePermissionSimpleDao rolePermissionSimpleDao;
    @Autowired
    private PermissionSimpleDao permissionSimpleDao;
    @Autowired
    private PermissionMenuService permissionMenuService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    public void setManagerSimpleDao(ManagerSimpleDao managerSimpleDao) {
        this.managerSimpleDao = managerSimpleDao;
    }

    @Test
    void contextLoads() {


    }
    @Test
    void list(){
        String sql=" SELECT COUNT(1) FROM t_manager o WHERE 1=1   ";
       // BigInteger totalByHql = managerSimpleDao.getTotalBySqlInt(sql, null);
        //System.out.println(totalByHql);
    }


}
