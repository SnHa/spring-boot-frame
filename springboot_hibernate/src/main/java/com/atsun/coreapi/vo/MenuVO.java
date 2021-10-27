package com.atsun.coreapi.vo;

import com.atsun.coreapi.enums.Scope;
import com.atsun.coreapi.po.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 */
@Data
public class MenuVO {
    /**
     *
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
    private String pid;

    /**
     * 范围
     */
    private String scope;

    /**
     * 排序编号
     */
    private int orderNum = 0;

    /**
     * 是否启用
     */
    private boolean enable = true;

    /**
     * 备注
     */
    private String remark;

    private List<MenuVO> children = new ArrayList<>();
}
