package com.atsun.coreapi.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>角色权限关联表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_role_permission", indexes = {
        @Index(name = "UK_role_id_permission_id", columnList = "roleId,permissionId", unique = true)
})
public class RolePermission extends BaseSnowflakeIdModel {

    private static final long serialVersionUID = 8599031416373616517L;

    /**
     * 角色
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    /**
     * 权限
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionId", nullable = false)
    private Permission permission;

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

}
