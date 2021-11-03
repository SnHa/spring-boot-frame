package com.atsun.coreapi.dto;

import com.atsun.coreapi.enums.AccountState;
import lombok.Data;

@Data
public class ManagerPageDTO extends PageDTO {

    private static final long serialVersionUID = -4841287690114782833L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 状态
     */
    private AccountState state;

}
