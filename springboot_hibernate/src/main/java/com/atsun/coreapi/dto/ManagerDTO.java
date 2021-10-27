package com.atsun.coreapi.dto;

import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.enums.Sexual;
import lombok.Data;

import java.util.List;


/**
 * @author HP
 */
@Data
public class ManagerDTO{
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
    private ManagerType type = ManagerType.SYSTEM;

    /**
     * 性别
     */
    private Sexual sexual = Sexual.MALE;

    /**
     *状态
     */

    private AccountState state = AccountState.NORMAL;

    /**
     * 角色
     */
    public List<String> listId;
}
