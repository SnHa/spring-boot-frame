package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.service.MenuService;
import com.atsun.coreapi.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SH
 */
@Api(tags = "菜单渲染")
@RequestMapping("/menu")
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取菜单")
    @GetMapping("/getTree")
    public DataResponse<List<MenuVO>> getTree() {
        return ok(menuService.getTree());
    }

}
