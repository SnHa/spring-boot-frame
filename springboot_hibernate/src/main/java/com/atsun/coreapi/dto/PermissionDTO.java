package com.atsun.coreapi.dto;

import com.atsun.coreapi.enums.PermissionType;
import com.atsun.coreapi.enums.Scope;
import com.atsun.coreapi.po.Permission;
import lombok.Data;

import java.util.List;

/**
 * @author SH
 */
@Data
public class PermissionDTO {

    /**
     * id
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
     * 权限
     */
    private String sn;

    /**
     * 父权限
     */
    private Permission parentPermission;

    /**
     * 范围
     */
    private Scope scope;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单id
     */
    List<String> listMenuId;

    public void setType(Object type) {
        this.type = type instanceof String ? PermissionType.valueOf((String) type) : (PermissionType) type;
    }

    public void setScope(Object scope) {
        this.scope = scope instanceof String ? Scope.getEnum((String) scope) : (Scope) scope;
    }

}
