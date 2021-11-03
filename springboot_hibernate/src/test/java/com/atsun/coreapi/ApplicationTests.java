package com.atsun.coreapi;

import com.atsun.coreapi.dao.*;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.vo.ManagerVO;
import com.atsun.coreapi.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
       /* TokenUtils tokenUtils = new TokenUtils();
        ManagerVO managerVO = tokenUtils.validationToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaXNzIjoic2giLCJhdWQiOiJzdW5oYW8iLCJleHAiOjE2MzU0MDg5ODUsImlhdCI6MTYzNTQwODg2NX0.SYSdqOCeDjJ0Ox2LWyN5EphqtkP9dCzBmvudx2_bKSM");
*/

        //System.out.println(managerVO);

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
    void add(){
        ManagerDTO manager = new ManagerDTO();
        manager.setPassword("123456");
        manager.setRealName("李四1");
        manager.setUsername("lis11i");
        manager.setType("SUPER");
        manager.setState("NORMAL");
        manager.setSexual("MALE");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        manager.setRoleIds(list);
        boolean save = managerService.edit(manager);
        System.out.println(save);
    }
    @Test
    void delete(){
            Boolean flag = managerService.delete("1453969475828187136");
            System.out.println(flag);
    }

    @Test
    void update(){
        ManagerDTO manager = new ManagerDTO();
        manager.setId("1454000576776896512");
        manager.setPassword("784578031");
        manager.setRealName("李四修改版");
        manager.setUsername("lis11ixiugai");
        manager.setType("SUPER");
        manager.setState("NORMAL");
        manager.setSexual("FEMALE");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        manager.setRoleIds(list);
        Boolean update = managerService.update(manager);
        System.out.println(update);
    }

    @Test
    void  query(){
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setPage(1);
        managerDTO.setSize(3);
        managerDTO.setUsername("n");
        managerDTO.setState("NORMAL");
        List<ManagerVO> manager= managerSimpleDao.getPage(managerDTO);
        System.out.println(manager.get(0));
        System.out.println(manager.get(1));
    }

    @Test
    void selectRole(){
        List<RoleVO> all = roleSimpleDao.getAll(1, 2);
        for (RoleVO r: all) {
            System.out.println(r);
        }
    }

    @Test
    void  addRole(){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("添加测试");
        roleDTO.setRemark("这就是一个测试");
        roleDTO.setScope("PLATFORM");
        Boolean add = roleService.add(roleDTO);

    }

    @Test
    void  updateRole(){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId("1455091395642523648");
        roleDTO.setName("修改测试");
        roleDTO.setRemark("备注数据");
        roleDTO.setScope("PLATFORM");
        Boolean update = roleService.update(roleDTO);
    }
    @Test
    void  te(){

        List<String> listRole = managerRoleService.getRoleIds("1");
        Set<String> listName = roleService.listName(listRole);
        // 查询授权信息
        // 根据角色id查询权限id
        List<String> listPermission = rolePermissionService.getPermissionIds(listRole);
        // 根据权限id查询sn
        Set<String> listSn = permissionService.getListSn(listPermission);
        Set<String> sn = new HashSet<>();
        for (String s : listSn) {
            String[] strings = s.split(",");
            for (int i = 0; i < strings.length; i++) {
                sn.add(strings[i]);
            }
        }

        for (String s1:sn) {
            System.out.println(s1);
        }
    }
}
