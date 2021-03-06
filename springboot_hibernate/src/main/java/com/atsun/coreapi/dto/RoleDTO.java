package com.atsun.coreapi.dto;

import com.atsun.coreapi.enums.Scope;
import lombok.Data;

import java.util.List;

/**
 * @author SH
 */
@Data
public class RoleDTO {

    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 范围
     */
    private Scope scope;


    /**
     * 备注
     */
    private String remark;

    /**
     * 权限id集合
     */
    private List<String> permissionIds;

    public void setScope(Object scope) {
        this.scope = scope instanceof String ? Scope.getEnum((String) scope) : (Scope) scope;
    }
}
