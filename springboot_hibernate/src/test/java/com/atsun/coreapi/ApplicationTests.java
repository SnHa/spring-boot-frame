package com.atsun.coreapi;

import com.atsun.coreapi.dao.*;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.ManagerRoleVO;
import com.atsun.coreapi.vo.ManagerVO;
import com.atsun.coreapi.vo.PermissionVO;
import com.atsun.coreapi.vo.RolePermissionVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

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
    public void setManagerSimpleDao(ManagerSimpleDao managerSimpleDao) {
        this.managerSimpleDao = managerSimpleDao;
    }

    @Test
    void contextLoads() {

        //String sql = "SELECT * FROM t_manager tm WHERE tm.username=:username";
/*
        String sql="select * from t_manager tm where tm.username=?";
        HashMap<String, Object> params = new HashMap<>();
        params.put("username","sunhao");

        ManagerVO vo = managerSimpleDao.getUserSql("sunhao");*/
/*        System.out.println(vo);*/
        List<ManagerVO> allManager = managerSimpleDao.getAll();
        for (ManagerVO mv :allManager) {
            System.out.println(mv.toString());
        }
    }
    @Test
    void list(){
        List<String> list = new ArrayList<>();
        list.add("ORGANIZATION");
        list.add("PLATFORM");
        List<PermissionVO> menuList = menuSimpleDao.getMenuListV(list);
        for (PermissionVO menu :menuList) {
            System.out.println(menu.toString());
        }
    }

    @Test
    void listScope(){
        List<String> listScope = managerScopeSimpleDao.getListScope("1");
        System.out.println(listScope.get(0));
    }
    @Test
    void listRoleId(){
        List<ManagerRoleVO> listRoleId = managerRoleSimpleDao.getListRoleId("1");
        for (ManagerRoleVO mv:listRoleId) {
            System.out.println(mv);
        }
        List<RolePermissionVO> listPermission = rolePermissionSimpleDao.getListPermission(listRoleId);
        for (RolePermissionVO RP:listPermission) {
            System.out.println(RP);
        }
    }
    @Test
    void  menu(){
        List<ManagerRoleVO> listRoleId = managerRoleSimpleDao.getListRoleId("1");
        List<RolePermissionVO> list =rolePermissionSimpleDao.getListPermission(listRoleId);
        List<PermissionVO> listMenu = permissionSimpleDao.getListMenu(list);
        String path = menuSimpleDao.getMenuPath("用户中心");
        System.out.println("================");
        System.out.println(path);
        TreeUtil treeUtil = new TreeUtil();
        List<PermissionVO> build = treeUtil.build(listMenu);
        for (PermissionVO pm :build) {
            System.out.println(pm);
        }
    }
    @Test
    void test(){
        Manager manager = new Manager();
        System.out.println(manager.getId());
        manager.setTokenVer(1111);
        manager.setEmail("784578031@qq.com");
        manager.setPassword("123456");
        manager.setRealName("李四1");
        manager.setUsername("lis11i");
        Manager save = managerSimpleDao.save(manager);
        System.out.println(save);
    }
    @Test
    void delete(){
        try {
            managerSimpleDao.deleteById("10");
        } catch (Exception e) {
            System.out.println("三处失败");
        }
    }
}
