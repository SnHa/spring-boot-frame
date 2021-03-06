package com.atsun.coreapi.po;

import com.atsun.coreapi.enums.Scope;
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
public class Role extends BaseSnowflakeIdModel {

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

    public Role(String id) {
        super(id);
    }

    public void setScope(Object scope) {
        this.scope = scope instanceof  String ? Scope.getEnum((String) scope): (Scope) scope;
    }
}
