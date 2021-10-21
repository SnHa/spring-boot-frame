package com.atsun.api.po;

import com.atsun.api.enums.ManagerType;
import com.atsun.api.enums.Sexual;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Description: Created by LD on 2019/11/04</p>
 * <p>管理员表</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_manager")
public class Manager extends Account {

    private static final long serialVersionUID = 2479800517256905184L;

    /**
     * 用户名
     */
    @Column(length = 32, nullable = false, unique = true)
    private String username;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 真实姓名
     */
    @Column(length = 32)
    private String realName;

    /**
     * 类型
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private ManagerType type = ManagerType.SYSTEM;

    /**
     * 性别
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private Sexual sexual = Sexual.MALE;

    /**
     * 头像附件
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "headImageAttId")
    private Att headImageAtt;

    /**
     * 头像地址
     */
    @Column
    private String headImageUrl;

    /**
     * 邮箱
     */
    @Column(length = 32)
    private String email;

    /**
     * 备注
     */
    @Column
    private String remark;

    public Manager(Long id) {
        super(id);
    }

    public static final List<String> CLONE_BEAN_IGNORE_PROPERTIES = new ArrayList<String>() {

        private static final long serialVersionUID = 5973066850995392366L;

        {
            addAll(DEFAULT_CLONE_BEAN_IGNORE_PROPERTIES);
            add("username");
            add("password");
        }
    };

    @Override
    protected List<String> cloneBeanIgnoreProperties() {
        return CLONE_BEAN_IGNORE_PROPERTIES;
    }

}
