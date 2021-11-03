package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.dto.MenuDTO;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.vo.MenuVO;
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
@Api(tags = "菜单渲染")
@RequestMapping("/menu")
@RestController
public class MenuController extends BaseController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "动态获取菜单项")
    @GetMapping("/select")
    public DataResponse select(HttpServletRequest request, HttpServletResponse response) {
        log.info("响应头获取token消息" + request.getHeader("token"));
        String token = request.getHeader("token");
        List<MenuVO> build = menuService.getAll(token);
        return ok(build);
    }

    // 查询所有菜单

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/query/{page}/{size}")
    public DataResponse query(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

        List<MenuVO> build = menuService.getAllMenu(page, size);
        return ok(build);
    }

    // 添加菜单

    @ApiOperation(value = "添加菜单")
    @PostMapping("/add")
    public NoDataResponse add(@RequestBody MenuDTO menuDTO) {

        Boolean flag = menuService.add(menuDTO);

        if (!flag) {
            return error();
        }
        return ok();
    }

    // 修改菜单

    @ApiOperation(value = "修改菜单")
    @PostMapping("/update")
    public NoDataResponse update(@RequestBody MenuDTO menuDTO) {

        Boolean flag = menuService.update(menuDTO);

        if (!flag) {
            return error();
        }
        return ok();
    }

    // 删除菜单和关联表

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) {

        Boolean flag = menuService.delete(id);

        if (!flag) {
            return error();
        }
        return ok();
    }
}
