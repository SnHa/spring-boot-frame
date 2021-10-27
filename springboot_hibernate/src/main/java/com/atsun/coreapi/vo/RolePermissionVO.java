package com.atsun.coreapi.vo;

import lombok.Data;

/**
 * @author HP
 */
@Data
public class RolePermissionVO {
    /**
     * 角色
     */
    private String roleId;

    /**
     * 权限
     */
    private String permissionId;
}
