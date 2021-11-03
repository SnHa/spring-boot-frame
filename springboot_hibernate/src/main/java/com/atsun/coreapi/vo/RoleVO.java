package com.atsun.coreapi.vo;

import com.atsun.coreapi.enums.Scope;
import lombok.Data;

import java.util.Date;


/**
 * @author SH
 */
@Data
public class RoleVO {

    /**
     * ID
     */

    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 范围
     */
    private Scope scope;

    /**
     * 创建时间
     */
    private Date createDatetime;

    /**
     * 修改时间
     */
    private Date updateDatetime;

    /**
     * 备注
     */
    private String remark;


    public void setScope(Object scope) {
        this.scope = scope instanceof String ? Scope.getEnum((String) scope) : (Scope) scope;
    }
}
