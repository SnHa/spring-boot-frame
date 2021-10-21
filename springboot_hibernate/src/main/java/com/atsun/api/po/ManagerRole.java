package com.atsun.api.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>管理员角色表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_manager_role", indexes = {
        @Index(name = "UK_manager_id_role_id", columnList = "managerId,roleId", unique = true)
})
public class ManagerRole extends BaseIncrementIdModel {

    private static final long serialVersionUID = -8595214994283867687L;

    /**
     * 管理员
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId", nullable = false)
    private Manager manager;

    /**
     * 角色
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    public ManagerRole(Manager manager, Role role) {
        this.manager = manager;
        this.role = role;
    }

}
