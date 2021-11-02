package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dao.PermissionMenuSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.dto.PermissionDTO;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.po.PermissionMenu;
import com.atsun.coreapi.service.PermissionService;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HP
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionSimpleDao permissionSimpleDao;

    private RolePermissionSimpleDao rolePermissionSimpleDao;

    private PermissionMenuSimpleDao permissionMenuSimpleDao;

    private MenuSimpleDao menuSimpleDao;

    @Autowired
    public void setRolePermissionSimpleDao(RolePermissionSimpleDao rolePermissionSimpleDao) {
        this.rolePermissionSimpleDao = rolePermissionSimpleDao;
    }

    @Autowired
    public void setPermissionMenuSimpleDao(PermissionMenuSimpleDao permissionMenuSimpleDao) {
        this.permissionMenuSimpleDao = permissionMenuSimpleDao;
    }

    @Autowired
    public void setPermissionSimpleDao(PermissionSimpleDao permissionSimpleDao) {
        this.permissionSimpleDao = permissionSimpleDao;
    }

    @Autowired
    public void setMenuSimpleDao(MenuSimpleDao menuSimpleDao) {
        this.menuSimpleDao = menuSimpleDao;
    }

    @Override
    public List<String> getListTypeMenu(List<String> listPermission) {
        return permissionSimpleDao.getListTypeMenu(listPermission);
    }

    @Override
    public Set<String> getListSn(List<String> listPermission) {
        List<String> listSn = permissionSimpleDao.getListSn(listPermission);

        HashSet<String> sn = new HashSet<>();

        for (String s : listSn) {
            sn.add(s);
        }
        return sn;
    }

    @Override
    public Boolean add(PermissionDTO permissionDTO) {

        Permission permission = new Permission();

        permission.setName(permissionDTO.getName());
        permission.setOrderNum(0);
        permission.setRemark(permissionDTO.getRemark());
        permission.setScope(permissionDTO.getScope());
        permission.setSn(permissionDTO.getSn());
        permission.setType(permissionDTO.getType());
        permission.setCreateDatetime(new Date());
        permission.setUpdateDatetime(new Date());

        //判断父节点id是否为空
        if (permissionDTO.getParentPermission().getId() != null) {
            // 根据id查询该父节点信息
            Permission parentPermission = permissionSimpleDao.getParent(permissionDTO.getParentPermission().getId());
            permission.setParentPermission(parentPermission);
        } else {
            permission.setParentPermission(null);
        }

        Permission save = permissionSimpleDao.save(permission);

        if (save == null) {
            log.error("======权限添加失败=======");
            return false;
        }

        // 权限-菜单添加
        for (String id : permissionDTO.getListMenuId()) {
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setPermission(save);
            permissionMenu.setCreateDatetime(new Date());
            permissionMenu.setUpdateDatetime(new Date());
            // 根据菜单id查询菜单数据
            Menu menu = menuSimpleDao.getById(id);
            permissionMenu.setMenu(menu);
            permissionMenuSimpleDao.save(permissionMenu);
        }

        return true;
    }

    @Override
    public Boolean update(PermissionDTO permissionDTO) {

        Permission permission = permissionSimpleDao.getPermission(permissionDTO.getId());

        permission.setName(permissionDTO.getName());
        permission.setRemark(permissionDTO.getRemark());
        permission.setScope(permissionDTO.getScope());
        permission.setSn(permissionDTO.getSn());
        permission.setType(permissionDTO.getType());
        permission.setUpdateDatetime(new Date());

        //判断父节点id是否为空
        if (permissionDTO.getParentPermission().getId() != null) {
            // 根据id查询该父节点信息
            Permission parentPermission = permissionSimpleDao.getParent(permissionDTO.getParentPermission().getId());
            permission.setParentPermission(parentPermission);
        } else {
            permission.setParentPermission(null);
        }

        Permission save = permissionSimpleDao.save(permission);

        if (save == null) {
            log.error("修改权限失败");
            return false;
        }

        //判断权限菜单数据是否为空
        List<String> permissionId = permissionMenuSimpleDao.getPermission(permissionDTO.getId());
        if (permissionId != null) {
            // 删除原有的权限-菜单
            permissionMenuSimpleDao.deletePermission(permissionDTO.getId());
        }

        // 添加现在的权限-菜单
        for (String id : permissionDTO.getListMenuId()) {
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setPermission(save);
            permissionMenu.setCreateDatetime(new Date());
            permissionMenu.setUpdateDatetime(new Date());
            // 根据菜单id查询菜单数据
            Menu menu = menuSimpleDao.getById(id);
            permissionMenu.setMenu(menu);
            permissionMenuSimpleDao.save(permissionMenu);
        }

        return true;
    }

    @Override
    public Boolean delete(String id) {

        // 判断权限是否存在
        if (permissionSimpleDao.getById(id) == null) {
            log.error("=============权限不存在===========");
            return false;
        }
        // 权限-角色
        int r = rolePermissionSimpleDao.deletePermission(id);
        if (r != 1) {
            log.error("===========权限-角色删除失败=============");
            return false;
        }
        // 权限-菜单
        int m = permissionMenuSimpleDao.deletePermission(id);
        if (m != 1) {
            log.error("==========权限-菜单删除失败=============");
            return false;
        }
        // 权限
        int p = permissionSimpleDao.delById(id);
        if (p != 1) {
            log.error("==========权限删除失败=============");
            return false;
        }
        return true;
    }

    @Override
    public Boolean addSon(PermissionDTO permissionDTO) {

        Permission permission = new Permission();

        permission.setName(permissionDTO.getName());
        permission.setOrderNum(0);
        permission.setRemark(permissionDTO.getRemark());
        permission.setScope(permissionDTO.getScope());
        permission.setSn(permissionDTO.getSn());
        permission.setType(permissionDTO.getType());
        permission.setCreateDatetime(new Date());
        permission.setUpdateDatetime(new Date());

        // 根据id查询该父节点信息
        Permission parentPermission = permissionSimpleDao.getParent(permissionDTO.getParentPermission().getId());
        permission.setParentPermission(parentPermission);

        Permission save = permissionSimpleDao.save(permission);

        if (save == null) {
            return false;
        }

        // 权限-菜单添加
        for (String id : permissionDTO.getListMenuId()) {
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setPermission(save);
            permissionMenu.setCreateDatetime(new Date());
            permissionMenu.setUpdateDatetime(new Date());
            // 根据菜单id查询菜单数据
            Menu menu = menuSimpleDao.getById(id);
            permissionMenu.setMenu(menu);
            permissionMenuSimpleDao.save(permissionMenu);
        }

        return true;
    }

    @Override
    public List<PermissionVO> getAll(Integer page, Integer size) {

        List<PermissionVO> list = permissionSimpleDao.getAll(page, size);
        List<PermissionVO> build = TreeUtil.buildP(list);
        return build;
    }


}
