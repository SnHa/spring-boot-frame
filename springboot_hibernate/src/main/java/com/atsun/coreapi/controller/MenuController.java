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

    @ApiOperation(value = "条件查询")
    @PostMapping("/query")
    public DataResponse<PageBean<MenuVO>> query(@RequestBody MenuPageDTO menuPageDTO) throws TransException {

        return ok(menuService.getAllMenu(menuPageDTO));
    }

    // 添加菜单

    @ApiOperation(value = "添加-修改菜单")
    @PostMapping("/edit")
    public NoDataResponse edit(@RequestBody MenuDTO menuDTO) throws TransException {

        menuService.edit(menuDTO);

        return ok();
    }

    // 删除菜单和关联表

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException {

        menuService.delete(id);

        return ok();
    }
}
