package com.atsun.coreapi.po;

import com.atsun.coreapi.enums.PermissionType;
import com.atsun.coreapi.enums.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>权限表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_permission")
public class Permission extends BaseUUIDModel {

    private static final long serialVersionUID = 5014082842481167037L;

    public static final String SN_SPIT_CHAR = ";";

    /**
     * 权限名
     */
    @Column(length = 64, nullable = false)
    private String name;

    /**
     * 类别
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private PermissionType type = PermissionType.MENU;

    /**
     * 权限
     */
    @Column
    private String sn;

    /**
     * 父权限
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pId", referencedColumnName = "id")
    private Permission parentPermission;

    /**
     * 范围
     */
    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private Scope scope = Scope.PLATFORM;

    /**
     * 排序编号
     */
    @Column(nullable = false)
    private int orderNum = 0;

    /**
     * 备注
     */
    @Column
    private String remark;

    public Permission(String id) {
        super(id);
    }

}
