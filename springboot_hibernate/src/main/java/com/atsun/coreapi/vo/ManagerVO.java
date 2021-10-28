package com.atsun.coreapi.vo;

import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.enums.Sexual;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SH
 */
@Data
public class ManagerVO implements Serializable {

    private static final long serialVersionUID = -5388129418805334162L;

    /**
     * Id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 类型
     */
    private ManagerType type;

    /**
     * 性别
     */
    private Sexual sexual;

    /**
     * 头像地址
     */
    private String headImageUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     *
     */
    private String tokenVer;

    /**
     * 账户状态
     */
    private AccountState state;

    /**
     * 最后登录时间
     */
    private Date lastLoginDatetime;

    public void setType(Object type) {
        this.type = type instanceof String ? ManagerType.getEnum((String) type) : (ManagerType) type;
    }

    public void setSexual(Object sexual) {
        this.sexual = sexual instanceof String ? Sexual.getEnum((String) sexual) : (Sexual) sexual;
    }

    public void setState(Object state) {
        this.state = state instanceof String ? AccountState.getEnum((String) state) : (AccountState) state;
    }

}
