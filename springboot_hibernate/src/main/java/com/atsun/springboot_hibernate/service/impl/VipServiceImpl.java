package com.atsun.springboot_hibernate.service.impl;

import com.atsun.springboot_hibernate.dao.VipRepository;
import com.atsun.springboot_hibernate.entity.VIP;
import com.atsun.springboot_hibernate.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private VipRepository vipRepository;

    @Override
    public List<VIP> queryVipList() {
        return vipRepository.findAll();
    }

    @Override
    public int saveVip(VIP vip) {
        VIP save = vipRepository.save(vip);
        System.out.println("========================");
        System.out.println(save);
        return 1;
    }

    @Override
    public VIP selectVip(Integer id) {
        return vipRepository.findById(id).get();
    }

    @Override
    public void deleteVip(Integer id) {
        vipRepository.deleteById(id);
    }

    @Override
    public VIP updateVip(VIP vip) {
        //根据id查询数据
        VIP vip1 = vipRepository.findById(vip.getId()).orElse(new VIP());
        //修改跟新数据
        vip1.setName(vip.getName());
        vip1.setBalance(vip.getBalance());
        //修改数据
        return vipRepository.save(vip1);
    }

    @Override
    public Page<VIP> listPage(Integer page, int i) {
        Sort orders = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page - 1, i, orders);
        Page<VIP> all = vipRepository.findAll(pageRequest);
        return all;
    }

    @Override
    public Optional<VIP> selectVipByName(String name) {
        //匿名内部内
        Specification<VIP> specification = new Specification<VIP>() {
            /**自定义查询条件
             *1.实现specification接口（提供泛型和查询对象类型）
             * 2.toPredicate方法（构造条件）
             * @param root 获取需要查询条件的属性
             * @param query
             * @param criteriaBuilder 构建条件，内部封装了多个查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                Path Name = root.get("name");
                Predicate predicate = criteriaBuilder.equal(Name, name);
                return predicate;
            }
        };
        return vipRepository.findOne(specification);
    }

}
