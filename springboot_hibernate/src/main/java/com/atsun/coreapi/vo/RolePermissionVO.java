package com.atsun.coreapi.vo;

import com.atsun.coreapi.po.Permission;
import com.atsun.coreapi.po.Role;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
