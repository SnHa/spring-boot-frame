package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.atsun.coreapi.enums.TransCode.SQL_INTEGRITY_CONSTRAINT_VIOLATION;

/**
 * 账户管理
 *
 * @author HP
 */
@Slf4j
@Api(tags = "账户管理")
@RequestMapping("/permission/account")
@RestController
public class ManagerUserController extends BaseController {

    private ManagerService managerService;

    @Autowired
    public ManagerUserController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation("分页查询所有的用户")
    @RequiresPermissions("manager:getPage")
    @GetMapping("/allList/{page}/{size}")
    public DataResponse getAllList(@PathVariable int page, @PathVariable int size,
                                   HttpServletRequest request) {
        // 封装pege
        Page pag = new Page(page, size);

        List<ManagerVO> managerList = managerService.getAllManager(pag);
        // 判断数据是否为空
        if (managerList == null) {
            return error();
        }
        return ok(managerList);
    }

    @ApiOperation(value = "添加管理用户")
    @RequiresPermissions("manager:add")
    @PostMapping("/add")
    public NoDataResponse addManager(@RequestBody ManagerDTO manager) {

        Boolean flag = managerService.addManager(manager);

        if (flag) {
            return ok();
        }
        return error();
    }

    @ApiOperation(value = "删除单个用户")
    @RequiresPermissions("manager:delete")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse deleteManager(@PathVariable String id) {

        Boolean flag = managerService.deleteManager(id);

        if (flag) {
            return ok();
        }
        return error();
    }

    @ApiOperation(value = "修改单个用户")
    @RequiresPermissions("manager:edit")
    @PostMapping("/update")
    public NoDataResponse update(@RequestBody ManagerDTO managerDTO) {

        Boolean flag = managerService.update(managerDTO);

        if (flag) {
            return ok();
        }
        return error();
    }

    @ApiOperation(value = "条件查询")
    @GetMapping("/condition/select")
    public DataResponse condition(@RequestBody ManagerDTO managerDTO) {

        List<ManagerVO> manager = managerService.conditionSelect(managerDTO);

        return ok(managerDTO);
    }
}
