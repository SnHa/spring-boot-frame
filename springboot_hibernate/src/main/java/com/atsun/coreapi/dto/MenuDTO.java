package com.atsun.coreapi.dto;

import com.atsun.coreapi.enums.Scope;
import com.atsun.coreapi.po.Menu;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author SH
 */
@Data
public class MenuDTO {

    /**
     * id
     */
    private String id;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 默认跳转地址
     */
    private String redirect;

    /**
     * 元信息 例：{title: '退货订单', keepAlive: true, icon: 'profile'}
     */
    private String meta;

    /**
     * 父菜单
     */
    private Menu parentMenu;

    /**
     * 范围
     */
    private Scope scope;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 备注
     */
    private String remark;
}
