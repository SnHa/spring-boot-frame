package com.atsun.springboot_hibernate.dao;

import com.atsun.springboot_hibernate.entity.Customer;
import com.atsun.springboot_hibernate.vo.CustomerVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
/*    @Query(value = "SELECT * FROM cst_customer LEFT JOIN cst_linkman  ON cst_customer.cust_id=cst_linkman.lmk_cust_id WHERE cst_customer.cust_id=?1"
            ,nativeQuery = true)*/
   /* @Query(value = "SELECT new com.atsun.springboot_hibernate.vo.CustomerVO(c.name,l.email)"+
            " FROM Customer c LEFT JOIN Linkman l ON c.id=l.id "+
            "WHERE c.id=:id ")
    CustomerVO selectById(@Param("id") Integer id);*/

}
