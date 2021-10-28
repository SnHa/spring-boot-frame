package com.atsun.coreapi;

import com.atsun.coreapi.dao.*;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.service.MenuService;
import com.atsun.coreapi.service.PermissionMenuService;
import com.atsun.coreapi.utils.TokenUtils;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.ManagerVO;
import com.atsun.coreapi.vo.MenuVO;
import com.atsun.coreapi.vo.PermissionVO;
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
    private PermissionMenuService permissionMenuService;
    @Autowired
    private MenuService menuService;
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
        List<String> listRoleId = managerRoleSimpleDao.getRoleIds("1");
        for (String mv:listRoleId) {
            System.out.println(mv);
        }
        List<String> listPermission = rolePermissionSimpleDao.getPermissionIds(listRoleId);
        for (String RP:listPermission) {
            System.out.println(RP);
        }
    }
    @Test
    void  menu(){
       // List<MenuVO>  build= menuService.getAll("1");
        TokenUtils tokenUtils = new TokenUtils();
        ManagerVO managerVO = tokenUtils.validationToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaXNzIjoic2giLCJhdWQiOiJzdW5oYW8iLCJleHAiOjE2MzU0MDg5ODUsImlhdCI6MTYzNTQwODg2NX0.SYSdqOCeDjJ0Ox2LWyN5EphqtkP9dCzBmvudx2_bKSM");

        System.out.println(managerVO);

        /* List<String> listRoleId = managerRoleSimpleDao.getRoleIds("1");
        List<String> list =rolePermissionSimpleDao.getPermissionIds(listRoleId);
        List<String> listMenu = permissionSimpleDao.getListTypeMenu(list);
        List<String> listMenuId=permissionMenuService.getListMenuId(listMenu);*/
       // List<MenuVO> menus=menuService.getAll(listMenuId);
       // TreeUtil.build(menus);
      //  System.out.println(listMenuId.get(0));
       /* String path = menuSimpleDao.getMenuPath("用户中心");
        System.out.println("================");
        System.out.println(path);
        TreeUtil treeUtil = new TreeUtil();
        List<PermissionVO> build = treeUtil.build(listMenu);
        for (PermissionVO pm :build) {
            System.out.println(pm);
        }*/
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
