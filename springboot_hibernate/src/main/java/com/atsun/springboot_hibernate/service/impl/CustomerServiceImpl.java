package com.atsun.springboot_hibernate.service.impl;

import com.atsun.springboot_hibernate.dao.CustomerRepository;
import com.atsun.springboot_hibernate.entity.Customer;
import com.atsun.springboot_hibernate.entity.Linkman;
import com.atsun.springboot_hibernate.service.CustomerService;
import com.atsun.springboot_hibernate.vo.CustomerVO;
import javassist.convert.Transformer;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.spi.NativeQueryImplementor;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CustomerVO selectById(Integer id) {
        String sql="SELECT * FROM cst_customer LEFT JOIN cst_linkman  ON cst_customer.cust_id=cst_linkman.lmk_cust_id WHERE cst_customer.cust_id=?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,id);
        List resultList = nativeQuery.getResultList();
        System.out.println(resultList);
        return null;
        //return customerRepository.selectById(id);
    }

    /**
     * 查询返回list集合
     * @param id
     * @return
     */
    @Override
    public List<Customer> queryById(Integer id) {
        String sql="SELECT * FROM cst_customer LEFT JOIN cst_linkman  ON cst_customer.cust_id=cst_linkman.lmk_cust_id WHERE cst_customer.cust_id=?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,id);
        List resultList = nativeQuery.getResultList();
        System.out.println(resultList);
        return resultList;
    }

    /**
     * 查询返回Map集合
     * @param id
     * @return
     */
    @Override
    public List queryByIdMap(Integer id) {
        String sql="SELECT * FROM cst_customer LEFT JOIN cst_linkman  ON cst_customer.cust_id=cst_linkman.lmk_cust_id WHERE cst_customer.cust_id=?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,id);
        List resultList = nativeQuery.getResultList();
        return resultList;
    }
    @Override
    public Customer insert(Customer customer) {
        Customer save = customerRepository.save(customer);
        return save;
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);

    }


}
