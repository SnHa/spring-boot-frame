package com.atsun.coreapi.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>权限菜单表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_permission_menu", indexes = {
        @Index(name = "UK_permission_id_menu_id", columnList = "permissionId,menuId", unique = true)
})
public class PermissionMenu extends BaseSnowflakeIdModel {

    private static final long serialVersionUID = 969703816686036400L;

    /**
     * 权限
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionId", nullable = false)
    private Permission permission;

    /**
     * 菜单
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;

    public PermissionMenu(Permission permission, Menu menu) {
        this.permission = permission;
        this.menu = menu;
    }

}
