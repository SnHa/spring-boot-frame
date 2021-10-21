package com.atsun.springboot_hibernate.po;

import com.atsun.springboot_hibernate.enums.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>角色表</p>
 *
 * @author LD
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "t_role")
public class Role extends BaseIncrementIdModel {

    private static final long serialVersionUID = 523493271315254724L;

    /**
     * 名称
     */
    @Column(nullable = false, length = 64, unique = true)
    private String name;

    /**
     * 范围
     */
    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private Scope scope = Scope.PLATFORM;

    /**
     * 备注
     */
    @Column
    private String remark;

    public Role(Long id) {
        super(id);
    }

}
