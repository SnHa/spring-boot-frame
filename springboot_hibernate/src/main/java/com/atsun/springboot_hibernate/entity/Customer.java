package com.atsun.springboot_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author HP
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cst_customer")
public class Customer {

    /**
     * 客户编号
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cust_id")
    private Integer id;

    /**
     * 客户名称
     */
    @Column(name = "cust_name")
    private String name;

    /**
     * 客户信息来源
     */
    @Column(name = "cust_source")
    private String source;

    /**
     * 客户所属行业
     */
    @Column(name = "cust_industry")
    private String industry;

    /**
     * 客户级别
     */
    @Column(name = "cust_level")
    private String level;

    /**
     * 客户联系地址
     */
    @Column(name = "cust_address")
    private String address;

    /**
     * 客户联系电话
     */
    @Column(name = "cust_phone")
    private String phone;

    /**
     * cascade配置级联关系
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Linkman> linkmanSet;

}
