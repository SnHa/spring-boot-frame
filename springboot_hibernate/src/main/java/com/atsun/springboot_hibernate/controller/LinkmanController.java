package com.atsun.springboot_hibernate.controller;

import com.atsun.springboot_hibernate.entity.Linkman;
import com.atsun.springboot_hibernate.service.LinkmanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@Api(tags = "联系人")
public class LinkmanController {
    @Autowired
    LinkmanService linkmanService;
    @GetMapping("list/{id}")
    public Linkman select(@PathVariable("id") Integer id) {
      Linkman linkman=  linkmanService.select(id);
       return linkman;
    }
}
