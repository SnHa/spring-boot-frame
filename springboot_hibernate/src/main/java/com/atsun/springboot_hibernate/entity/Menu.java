package com.atsun.springboot_hibernate.entity;

import com.atsun.springboot_hibernate.enums.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>菜单表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_menu")
public class Menu extends BaseIncrementIdModel {

    private static final long serialVersionUID = -7646452093379774040L;

    /**
     * 菜单标题
     */
    @Column(nullable = false, length = 64)
    private String title;

    /**
     * 菜单名称
     */
    @Column(nullable = false, length = 32)
    private String name;

    /**
     * 菜单地址
     */
    @Column(length = 128)
    private String path;

    /**
     * 组件
     */
    @Column(length = 64)
    private String component;

    /**
     * 默认跳转地址
     */
    @Column(length = 128)
    private String redirect;

    /**
     * 元信息 例：{title: '退货订单', keepAlive: true, icon: 'profile'}
     */
    @Column
    private String meta;

    /**
     * 父菜单
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pId", referencedColumnName = "id")
    private Menu parentMenu;

    /**
     * 范围
     */
    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private Scope scope = Scope.PLATFORM;

    /**
     * 排序编号
     */
    @Column(nullable = false)
    private int orderNum = 0;

    /**
     * 是否启用
     */
    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    private boolean enable = true;

    /**
     * 备注
     */
    @Column
    private String remark;

}
