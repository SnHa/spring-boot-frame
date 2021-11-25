package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.dto.RolePageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.RoleService;
import com.atsun.coreapi.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author SH
 */
@Slf4j
@Api(tags = "角色管理")
@RequestMapping("/permission/role")
@RestController
public class RoleController extends BaseController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "角色查询")
    @RequiresPermissions("role:getPage")
    @PostMapping("/list")
    public DataResponse<PageBean<RoleVO>> list(@RequestBody RolePageDTO rolePageDTO) throws TransException {

        return ok(roleService.getAll(rolePageDTO));
    }

    @ApiOperation("删除角色")
    @RequiresPermissions("role:delete")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String roleId) throws TransException {

        roleService.delete(roleId);

        return ok();
    }

    @ApiOperation("添加-修改角色基本信息")
    @RequiresPermissions("role:edit")
    @PostMapping("/edit")
    public NoDataResponse edit(@RequestBody RoleDTO roleDTO) throws TransException {

        roleService.edit(roleDTO);

        return ok();
    }

    @ApiOperation(value = "根据id查询单个角色做回显")
    @GetMapping("/query/{id}")
    public DataResponse<Role> query(@PathVariable("id") String roleId) throws TransException {

        return ok(roleService.query(roleId));

    }

    @ApiOperation(value = "角色添加权限管理")
    @RequiresPermissions("role:authorize")
    @PostMapping("/add/permission")
    public NoDataResponse rolePermission(@RequestBody RoleDTO roleDTO) throws TransException {

        roleService.addPermission(roleDTO);

        return ok();

    }
}
