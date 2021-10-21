package com.atsun.springboot_hibernate.controller;

import com.atsun.springboot_hibernate.entity.VIP;
import com.atsun.springboot_hibernate.service.VipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vip")
@Api(tags = "vip理发店")
public class VipController {
    @Autowired
    private VipService vipService;
    //返回所有的信息
    @ApiOperation(value = "查询所有的会员")
    @GetMapping("list")
    public List<VIP> list(){
        List<VIP> list=vipService.queryVipList();
        return list;
    }

    @ApiOperation(value = "添加会员信息")
    @PostMapping("save")
    public String save(@RequestBody VIP vip){
        //判断传入参数是否为空
        if (vip!=null){
           int result= vipService.saveVip(vip);
           //判断是否添加成功
            if (result==1){
                return "添加成功";
            }else {
                return "添加失败";
            }
        }else {
            return "传入参数为null";
        }
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("select/{id}")
    public VIP select(@PathVariable("id") Integer id){
        return vipService.selectVip(id);
    }
    @ApiOperation(value = "删除会员数据")
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        vipService.deleteVip(id);
        return "删除数据成功";
    }

    @ApiOperation(value = "跟新会员数据")
    @PutMapping("update")
    public VIP update(@RequestBody VIP vip){
      return   vipService.updateVip(vip);
    }

    @ApiOperation(value = "会员信息分页")
    @GetMapping("listPage/{page}")
    public Page<VIP> listPage(@PathVariable("page") Integer page ){
       return vipService.listPage(page,3);
    }
    //SpecificationExecutor
    //动态查询
    @ApiOperation(value = "根据名字查询")
    @GetMapping("spec/select/{name}")
    public Optional<VIP> specSelect(@PathVariable("name") String name){
      return   vipService.selectVipByName(name);
    }


}
