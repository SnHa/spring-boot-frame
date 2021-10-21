package com.atsun.springboot_hibernate.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atsun.springboot_hibernate.entity.Customer;

import com.atsun.springboot_hibernate.entity.Linkman;
import com.atsun.springboot_hibernate.service.CustomerService;
import com.atsun.springboot_hibernate.util.Result;
import com.atsun.springboot_hibernate.vo.CustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cust")
@Api(tags = "客户")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ApiOperation("查询客户数据")
    @GetMapping("select/{id}")
    public Result<?> select(@PathVariable("id") Integer id) {
        CustomerVO customer = customerService.selectById(id);
        System.out.println(customer.getName());
        return Result.OK(customer);
    }
    @ApiOperation("查询客户数据list")
    @GetMapping("query/{id}")
    public Result<?> queryList(@PathVariable("id") Integer id) {
        List customer = customerService.queryById(id);
        return Result.OK(customer);
    }
    @ApiOperation("查询客户数据map")
    @GetMapping("queryMap/{id}")
    public Result<?> queryMap(@PathVariable("id") Integer id) {
        List customer = customerService.queryByIdMap(id);
        return Result.OK(customer);
    }
    @ApiOperation("删除客户数据")
    @DeleteMapping("delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        customerService.delete(id);
        return Result.OK();
    }

    @ApiOperation("插入客户数据")
    @PostMapping("insert")
    public Result<?> insert(@RequestBody Customer customer) {
        Customer insert = customerService.insert(customer);
        System.out.println(insert);
        return Result.OK();
    }
}
