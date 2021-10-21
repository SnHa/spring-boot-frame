package com.atsun.springboot_hibernate.dao;

import com.atsun.springboot_hibernate.entity.VIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VipRepository extends JpaRepository<VIP,Integer>, JpaSpecificationExecutor<VIP> {

}
