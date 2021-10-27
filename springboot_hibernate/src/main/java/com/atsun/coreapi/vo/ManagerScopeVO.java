package com.atsun.coreapi.vo;

import com.atsun.coreapi.enums.Scope;
import com.atsun.coreapi.po.BaseSnowflakeIdModel;
import com.atsun.coreapi.po.Manager;
import lombok.Data;


import javax.persistence.*;
@Data
public class ManagerScopeVO {

    /**
     * 管理员
     */
    private Manager manager;

    /**
     * 范围
     */
    private Scope scope;

}
