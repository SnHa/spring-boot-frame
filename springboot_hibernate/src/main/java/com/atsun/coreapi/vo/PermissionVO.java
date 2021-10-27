package com.atsun.coreapi.vo;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;


/**
 * @author HP
 */
@Data
public class PermissionVO {
    /**
     * 权限id
     */
    private String id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 类别
     */
    private String type;


    /**
     * 父权限
     */
    private String pid;

    /**
     * 范围
     */
    private String scope;


    /**
     * 备注
     */
    private String remark;

    /**
     * 路径
     */
    private String path;
    private List<PermissionVO> children = new ArrayList<>();

}
