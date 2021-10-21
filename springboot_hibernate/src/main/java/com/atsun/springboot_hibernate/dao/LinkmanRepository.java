package com.atsun.springboot_hibernate.dao;

import com.atsun.springboot_hibernate.entity.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkmanRepository extends JpaRepository<Linkman,Integer>, JpaSpecificationExecutor {
}
