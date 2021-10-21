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
    @Id
    @GeneratedValue(strategy =IDENTITY )//自动增长
    private Integer id;//主键
    @Column(name = "name")
    private String name;//姓名
    private String tel;//电话
    private BigDecimal balance;//余额
    private String card_no;//卡号
    private String password;//密码
    private Date caret_date;//创建日期
}
