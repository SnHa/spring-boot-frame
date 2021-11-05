package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.PermissionDTO;
import com.atsun.coreapi.dto.PermissionPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.service.PermissionService;
import com.atsun.coreapi.vo.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SH
 */
@Slf4j
@Api(tags = "权限管理")
@RequestMapping("/permission")
@RestController
public class PermissionController extends BaseController {

    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // 添加

    @ApiOperation(value = "添加-修改权限管理")
    @PostMapping("/edit")
    public NoDataResponse edit(@RequestBody PermissionDTO permissionDTO) throws TransException {

        permissionService.edit(permissionDTO);

        return ok();
    }

    //单个删除

    @ApiOperation(value = "单个删除")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException {

        permissionService.delete(id);

        return ok();
    }

    //批量删除

    //添加子菜单

    @ApiOperation(value = "添加子权限管理")
    @PostMapping("/addSon")
    public NoDataResponse addSon(@RequestBody PermissionDTO permissionDTO) throws TransException {

        permissionService.addSon(permissionDTO);

        return ok();
    }

    //查询全部

    @ApiOperation(value = "查询全部信息")
    @PostMapping("/selectList")
    public DataResponse<PageBean<PermissionVO>> selectList(@RequestBody PermissionPageDTO permissionPageDTO) throws TransException {

        return ok(permissionService.getAll(permissionPageDTO));

    }


}
