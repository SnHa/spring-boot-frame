package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.MenuDTO;
import com.atsun.coreapi.dto.MenuPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.service.MenuService;
import com.atsun.coreapi.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public DataResponse<List<MenuVO>> select(HttpServletRequest request) {

        log.info("响应头获取token消息" + request.getHeader("token"));
        String token = request.getHeader("token");

        return ok(menuService.getAll(token));
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/query")
    public DataResponse<PageBean<MenuVO>> query(@RequestBody MenuPageDTO menuPageDTO) throws TransException {

        return ok(menuService.getAllMenu(menuPageDTO));

    }

    @ApiOperation(value = "子查询")
    @PostMapping("/query/{pid}")
    public DataResponse<List<MenuVO>> queryPid(@PathVariable("pid") String pid) throws TransException {

        return ok(menuService.getAllSubmenu(pid));

    }

    @ApiOperation(value = "添加-修改菜单")
    @PostMapping("/edit")
    public NoDataResponse edit(@RequestBody MenuDTO menuDTO) throws TransException {

        menuService.edit(menuDTO);

        return ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String menuId) throws TransException {

        menuService.delete(menuId);

        return ok();
    }
}
