package com.atsun.springboot_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cst_linkman")
public class Linkman {

    /**
     * 联系人主键号
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "lkm_id")
    private Integer id;

    /**
     * 联系人
     */
    @Column(name = "lkm_name")
    private String name;

    /**
     * 性别
     */
    @Column(name = "lmk_gender")
    private String gender;

    /**
     * 电话
     */
    @Column(name = "lmk_phone")
    private String phone;

    /**
     * 联系人手机
     */
    @Column(name = "lmk_mobile")
    private String mobile;

    /**
     * 联系人邮箱
     */
    @Column(name = "lmk_email")
    private String email;

    /**
     * 联系人职位
     */
    @Column(name = "lmk_position")
    private String position;

    /**
     * 联系人备注
     */
    @Column(name = "LMK_memo")
    private String memo;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lmk_cust_id", referencedColumnName = "cust_id")
    private Customer customer;

}
