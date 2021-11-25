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

    @ApiOperation(value = "添加-修改权限管理")
    @PostMapping("/edit")
    public NoDataResponse edit(@RequestBody PermissionDTO permissionDTO) throws TransException {

        permissionService.edit(permissionDTO);

        return ok();
    }

    @ApiOperation(value = "单个删除")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String permissionId) throws TransException {

        permissionService.delete(permissionId);

        return ok();
    }

    @ApiOperation(value = "查询全部信息")
    @PostMapping("/selectList")
    public DataResponse<PageBean<PermissionVO>> selectList(@RequestBody PermissionPageDTO permissionPageDTO) throws TransException {

        return ok(permissionService.getAll(permissionPageDTO));

    }


    @ApiOperation(value = "查询子全部信息")
    @PostMapping("/select/{pid}")
    public DataResponse<List<PermissionVO>> selectSubmenu(@PathVariable("pid") String pid) throws TransException {

        return ok(permissionService.getAllSubPermission(pid));

    }

}
