package com.atsun.springboot_hibernate.vo;

import com.atsun.springboot_hibernate.entity.Linkman;
import lombok.*;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {
    /**
     * 客户编号
     */
    private Integer id;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户信息来源
     */
    private String source;
    /**
     * 客户所属行业
     */
    private String industry;
    /**
     * 客户级别
     */
    private String level;
    /**
     * 客户联系地址
     */
    private String address;
    /**
     * 客户联系电话
     */
    private String phone;

   private List<Linkman> linkmen;
}
