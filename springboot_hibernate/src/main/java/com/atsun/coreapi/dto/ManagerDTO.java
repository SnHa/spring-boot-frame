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
public class ManagerDTO {

    /**
     * id
     */
    private  String id;

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
    public List<String> listId;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页记录条数
     */
    private  Integer size;


    public void setType(Object type) {
        this.type = type instanceof  String ? ManagerType.getEnum((String) type) : (ManagerType) type;
    }

    public void setSexual(Object sexual) {
        this.sexual = sexual instanceof  String ? Sexual.getEnum((String) sexual) : (Sexual) sexual;
    }

    public void setState(Object state) {
        this.state = state instanceof  String ? AccountState.getEnum((String) state) : (AccountState) state;
    }
}
