package com.atsun.springboot_hibernate.service;

import com.atsun.springboot_hibernate.entity.Customer;
import com.atsun.springboot_hibernate.vo.CustomerVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {


    CustomerVO selectById(Integer id);


    Customer insert(Customer customer);

    void delete(Integer id);

    List queryById(Integer id);

    List queryByIdMap(Integer id);
}
