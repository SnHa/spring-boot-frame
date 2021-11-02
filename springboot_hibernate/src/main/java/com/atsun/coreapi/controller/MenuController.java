package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.service.*;
import com.atsun.coreapi.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public DataResponse select( HttpServletRequest request, HttpServletResponse response) {
        log.info( "响应头获取token消息"+request.getHeader("token"));
        String token=request.getHeader("token");
        List<MenuVO> build = menuService.getAll(token);
        return ok(build);
    }
}
