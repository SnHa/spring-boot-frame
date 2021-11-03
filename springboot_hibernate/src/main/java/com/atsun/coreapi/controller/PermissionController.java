package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.dto.PermissionDTO;
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
@RequestMapping("/permission1")
@RestController
public class PermissionController extends BaseController {

    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // 添加

    @ApiOperation(value = "添加权限管理")
    @PostMapping("/add")
    public NoDataResponse add(@RequestBody PermissionDTO permissionDTO) {
        Boolean flag = permissionService.add(permissionDTO);
        if (!flag) {
            return error();
        }
        return ok();
    }

    //修改

    @ApiOperation(value = "修改权限管理")
    @PostMapping("/update")
    public NoDataResponse update(@RequestBody PermissionDTO permissionDTO) {

        Boolean flag = permissionService.update(permissionDTO);
        if (!flag) {
            return error();
        }
        return ok();
    }

    //单个删除

    @ApiOperation(value = "单个删除")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) {

        Boolean flag = permissionService.delete(id);
        if (!flag) {
            return error();
        }
        return ok();
    }

    //批量删除

    //添加子菜单

    @ApiOperation(value = "添加子权限管理")
    @PostMapping("/addSon")
    public NoDataResponse addSon(@RequestBody PermissionDTO permissionDTO) {
        Boolean flag = permissionService.addSon(permissionDTO);
        if (!flag) {
            return error();
        }
        return ok();
    }

    //查询全部

    @ApiOperation(value = "查询全部信息")
    @GetMapping("/selectList/{page}/{size}")
    public DataResponse selectList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

        List<PermissionVO> permissionList = permissionService.getAll(page, size);

        return ok(permissionList);

    }


}
