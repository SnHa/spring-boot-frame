package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.dto.ManagerPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账户管理
 *
 * @author HP
 */
@Slf4j
@Api(tags = "账户管理")
@RequestMapping("/manager")
@RestController
public class ManagerController extends BaseController {

    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation(value = "编辑管理员")
    @RequiresPermissions("manager:edit")
    @PostMapping("/edit")
    public NoDataResponse edit(@RequestBody ManagerDTO dto) throws TransException {

        managerService.edit(dto);

        return ok();
    }

    @ApiOperation(value = "删除单个用户")
    @RequiresPermissions("manager:delete")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable String id) throws TransException {

        managerService.delete(id);

        return ok();
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/getPage")
    public DataResponse<PageBean<ManagerVO>> getPage(@RequestBody ManagerPageDTO dto) throws TransException {

        return ok(managerService.getPage(dto));

    }

}
