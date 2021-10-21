package com.atsun.springboot_hibernate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author HP
 * 状态码枚举类
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /**
     *成功
     */
    SUCCEED(200,"操作成功"),
    /**
     * 失败
     */
    FAIL(500,"操作失败"),
    /**
     *没有操作权限
     */
    NOT_AUTHORIZED(40300,"没有操作权限"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(50000, "系统异常");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String desc;
}
