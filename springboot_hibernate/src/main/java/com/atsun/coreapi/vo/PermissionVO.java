package com.atsun.coreapi.vo;

import com.atsun.coreapi.enums.PermissionType;
import com.atsun.coreapi.enums.Scope;
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
    private PermissionType type;


    /**
     * 父权限
     */
    private String pid;

    /**
     * 范围
     */
    private Scope scope;


    /**
     * 备注
     */
    private String remark;


    private List<PermissionVO> children = new ArrayList<>();

    public void setType(Object type) {
        this.type = type instanceof String ? PermissionType.valueOf((String) type) : (PermissionType) type;
    }

    public void setScope(Object scope) {
        this.scope = scope instanceof String ? Scope.getEnum((String) scope) : (Scope) scope;
    }

}
