package com.atsun.coreapi.service.impl;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.MenuSimpleDao;
import com.atsun.coreapi.dao.PermissionMenuSimpleDao;
import com.atsun.coreapi.dao.PermissionSimpleDao;
import com.atsun.coreapi.dao.RolePermissionSimpleDao;
import com.atsun.coreapi.dto.PermissionDTO;
import com.atsun.coreapi.dto.PermissionPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Menu;
import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.po.PermissionMenu;
import com.atsun.coreapi.service.PermissionService;
import com.atsun.coreapi.utils.TreeUtil;
import com.atsun.coreapi.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.atsun.coreapi.enums.TransCode.CUSTOM_EXCEPTION_MSG;

/**
 * @author HP
 */
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
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

        return new HashSet<>(listSn);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(PermissionDTO permissionDTO) throws TransException {

        Permission permission;

        // 判断PermissionDTO是否存在id
        if (StringUtils.isNotBlank(permissionDTO.getId())) {
            // 存在
            permission = permissionSimpleDao.getPermission(permissionDTO.getId());
            permission.setUpdateDatetime(new Date());

            // 删除权限--菜单表已经存在数据
            //判断权限菜单数据是否为空
            List<String> permissionId = permissionMenuSimpleDao.getPermission(permissionDTO.getId());
            if (permissionId != null) {
                // 删除原有的权限-菜单
                permissionMenuSimpleDao.deletePermission(permissionDTO.getId());
            }

        } else {
            // 不存在
            permission = new Permission();
        }

        permission.setOrderNum(0);
        permission.setName(permissionDTO.getName());
        permission.setRemark(permissionDTO.getRemark());
        permission.setScope(permissionDTO.getScope());
        permission.setSn(permissionDTO.getSn());
        permission.setType(permissionDTO.getType());

        //判断父节点id是否为空
        if (permissionDTO.getParentPermission().getId() != null) {
            // 根据id查询该父节点信息
            Permission parentPermission = permissionSimpleDao.getParent(permissionDTO.getParentPermission().getId());
            permission.setParentPermission(parentPermission);
        } else {
            permission.setParentPermission(null);
        }

        permissionSimpleDao.save(permission);

        // 权限-菜单添加
        for (String id : permissionDTO.getListMenuId()) {
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setPermission(permission);
            // 根据菜单id查询菜单数据
            Menu menu = menuSimpleDao.getById(id);
            permissionMenu.setMenu(menu);
            permissionMenuSimpleDao.save(permissionMenu);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws TransException {

        // 判断权限是否存在
        if (StringUtils.isBlank(permissionSimpleDao.getById(id).getId())) {
            throw new TransException(CUSTOM_EXCEPTION_MSG, "权限不存在");
        }
        // 权限-角色
        int r = rolePermissionSimpleDao.deletePermission(id);
        if (r != 1) {
            throw new TransException(CUSTOM_EXCEPTION_MSG, "权限-角色删除失败");
        }
        // 权限-菜单
        int m = permissionMenuSimpleDao.deletePermission(id);
        if (m != 1) {

            throw new TransException(CUSTOM_EXCEPTION_MSG, "权限-菜单删除失败");

        }
        // 权限
        int p = permissionSimpleDao.delById(id);
        if (p != 1) {

            throw new TransException(CUSTOM_EXCEPTION_MSG, "权限删除失败");

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSon(PermissionDTO permissionDTO) {

        Permission permission = new Permission();

        permission.setName(permissionDTO.getName());
        permission.setOrderNum(0);
        permission.setRemark(permissionDTO.getRemark());
        permission.setScope(permissionDTO.getScope());
        permission.setSn(permissionDTO.getSn());
        permission.setType(permissionDTO.getType());

        // 根据id查询该父节点信息
        Permission parentPermission = permissionSimpleDao.getParent(permissionDTO.getParentPermission().getId());
        permission.setParentPermission(parentPermission);

        Permission save = permissionSimpleDao.save(permission);


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

    }

    @Override
    public PageBean<PermissionVO> getAll(PermissionPageDTO permissionPageDTO) throws TransException {

        // 顶级权限
        PageBean<PermissionVO> list = permissionSimpleDao.getAll(permissionPageDTO.getPage(), permissionPageDTO.getName());
        // 得到顶级权限id集合
        ArrayList<String> perIds = new ArrayList<>();
        for (PermissionVO pv : list.getRecords()) {
            perIds.add(pv.getId());
        }

        // 子权限查询
        List<PermissionVO> pid = permissionSimpleDao.getByPid(perIds);

        // 数据添加到分页数据中
        for (PermissionVO pv : pid) {
            list.getRecords().add(pv);
        }

        list.setRecords(TreeUtil.buildP(list.getRecords()));

        return list;
    }


}
