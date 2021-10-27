package com.atsun.coreapi.controller;

import com.atsun.coreapi.dt.ManagerDTO;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.service.ManagerService;
import com.atsun.coreapi.vo.ManagerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.atsun.coreapi.enums.TransCode.SQL_INTEGRITY_CONSTRAINT_VIOLATION;

/**账户管理
 * @author HP
 */
@Api(tags = "账户管理")
@RequestMapping("/permission/account")
@RestController
public class ManagerUserController {
    @Autowired
    private ManagerService managerService;
    @ApiOperation("查询所有的用户")
    @GetMapping("allList")
    public Object getAllList(){
       List<ManagerVO> managerList= managerService.getAllManager();
        return new BaseController().ok(managerList);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public Object addManager(@RequestBody ManagerDTO manager){
       Manager flag= managerService.addManager(manager);
       if (flag==null){
           return  new BaseController().error(SQL_INTEGRITY_CONSTRAINT_VIOLATION);
       }
        return new BaseController().ok();
    }

    @ApiOperation(value = "删除单个用户")
    @DeleteMapping("delete/{id}")
    public Object deleteManager(@PathVariable String id){
      Boolean  flag =managerService.deleteManager(id);
      if (flag){
          return new BaseController().ok();
      }
        return new BaseController().error(SQL_INTEGRITY_CONSTRAINT_VIOLATION);
    }

    @ApiOperation(value = "修改单个用户")
    @PostMapping("update")
    public Object update(){
        return null;
    }

}
