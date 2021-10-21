package com.atsun.coreapi.bean;

import lombok.Data;

import static com.atsun.coreapi.enums.StatusEnum.FAIL;
import static com.atsun.coreapi.enums.StatusEnum.SUCCEED;

/**
 * @author SH
 * 返回结果
 */
@Data
public class Result<T> {

    /**
     * 返回状态
     */
    private boolean flag;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> ok() {
        return result(true, SUCCEED.getCode(), SUCCEED.getDesc(), null);
    }

    public static <T> Result<T> ok(T data) {
        return result(true, SUCCEED.getCode(), SUCCEED.getDesc(), data);
    }

    public static <T> Result<T> ok(T data, String message) {
        return result(true, SUCCEED.getCode(), message, data);
    }

    public static <T> Result<T> fail() {
        return result(false, FAIL.getCode(), FAIL.getDesc(), null);
    }

    public static <T> Result<T> fail(T data) {
        return result(false, FAIL.getCode(), FAIL.getDesc(), data);
    }

    public static <T> Result<T> result(Boolean flag, Integer code, String message, T data) {

        Result<T> result = new Result<>();

        result.setFlag(flag);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);

        return result;
    }

}
