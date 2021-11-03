package com.atsun.coreapi.dto;

import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.enums.Sexual;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author HP
 */
@Data
public class ManagerDTO implements Serializable {

    private static final long serialVersionUID = 6582022859321198158L;

    /**
     * id
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
     * 状态
     */
    private AccountState state;

    /**
     * 角色
     */
    public List<String> roleIds;

}
