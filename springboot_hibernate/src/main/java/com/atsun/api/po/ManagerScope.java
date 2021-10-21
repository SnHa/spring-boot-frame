package com.atsun.api.po;

import com.atsun.api.enums.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_manager_scope", indexes = {
        @Index(name = "UK_manager_id_scope", columnList = "managerId,scope", unique = true)
})
public class ManagerScope extends BaseIncrementIdModel {

    private static final long serialVersionUID = -9206153926879467419L;

    /**
     * 管理员
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId", nullable = false)
    private Manager manager;

    /**
     * 范围
     */
    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private Scope scope = Scope.PLATFORM;

}
