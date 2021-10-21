package com.atsun.springboot_hibernate.service;

import com.atsun.springboot_hibernate.entity.VIP;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface VipService {

    List<VIP> queryVipList();

    int saveVip(VIP vip);

    VIP selectVip(Integer id);

    void deleteVip(Integer id);

    VIP updateVip(VIP vip);

    Page<VIP> listPage(Integer page, int i);

    Optional<VIP> selectVipByName(String name);

}
