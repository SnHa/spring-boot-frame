package com.atsun.coreapi.vo;

import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.po.Role;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author SH
 */
@Data
public class ManagerRoleVO {

    /**
     * 管理员
     */
    private String managerId;

    /**
     * 角色
     */
    private String roleId;
}
