package com.atsun.springboot_hibernate.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "t_vip")
public class VIP {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建日期
     */
    private Date createDate;

}
