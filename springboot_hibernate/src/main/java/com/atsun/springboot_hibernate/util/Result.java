package com.atsun.springboot_hibernate.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.atsun.springboot_hibernate.enums.StatusEnum.FAIL;
import static com.atsun.springboot_hibernate.enums.StatusEnum.SUCCEED;

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
     *返回消息
     */
    private String message;

    /**
     *返回数据
     */
    private T data;

    public static <T> Result<T> OK(){return restResult(true,SUCCEED.getCode(),SUCCEED.getDesc(),null);}

    public static <T> Result<T> OK(T data){return restResult(true,SUCCEED.getCode(),SUCCEED.getDesc(),data);}

    public static <T> Result<T> OK(T data,String message){return restResult(true,SUCCEED.getCode(),message,data);}

    public static <T> Result<T> fail(){return restResult(false, FAIL.getCode(),FAIL.getDesc(),null);}

    private static <T> Result<T> fail(T data){return restResult(false,FAIL.getCode(),FAIL.getDesc(),data);}

    public static <T> Result<T> restResult(Boolean flag,Integer code,String message,T data){
        Result<T> apiResult = new Result<>();
        apiResult.setFlag(flag);
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(message);
        return apiResult;
    }
}
