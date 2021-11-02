package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.dto.RoleDTO;
import com.atsun.coreapi.po.Role;
import com.atsun.coreapi.service.RoleService;
import com.atsun.coreapi.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @GetMapping("/list/{page}/{size}")
    public DataResponse list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        // 直接查询角色表带分页
        List<RoleVO> roleList = roleService.getAll(page, size);
        return ok(roleList);
    }
    // 删除

    @ApiOperation("删除角色")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) {
        Boolean flag = roleService.delete(id);
        if (!flag) {
            return error();
        }
        return ok();
    }

    @ApiOperation("添加角色")
    @PostMapping("/add")
    public NoDataResponse add(@RequestBody RoleDTO roleDTO) {
        Boolean flag = roleService.add(roleDTO);
        if (!flag) {
            return error();
        }
        return ok();
    }

    @ApiOperation("修改角色基本信息")
    @PostMapping("/update")
    public NoDataResponse update(@RequestBody RoleDTO roleDTO) {
        Boolean flag = roleService.update(roleDTO);
        if (!flag) {
            return error();
        }
        return ok();
    }

    @ApiOperation(value = "根据id查询单个角色做回显")
    @GetMapping("/query/{id}")
    public DataResponse query(@PathVariable("id") String id) {
        Role role = roleService.query(id);
        return ok(role);
    }

    // 角色授权 权限

    @ApiOperation(value = "角色添加权限管理")
    @PostMapping("/add/permission")
    public NoDataResponse rolePermission() {
        return null;
    }
}
