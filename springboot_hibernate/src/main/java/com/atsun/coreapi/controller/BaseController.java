package com.atsun.coreapi.controller;

import com.atsun.coreapi.bean.DataResponse;
import com.atsun.coreapi.bean.NoDataResponse;
import com.atsun.coreapi.enums.TransCode;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SH
 */
@Api
@RequestMapping(value = BaseController.BASE_URL)
@RestController
public class BaseController {

    public static final String BASE_URL = "api";

    protected NoDataResponse ok() {
        return new NoDataResponse();
    }

    protected <T> DataResponse<T> ok(T data) {
        return new DataResponse<>(data);
    }

    protected NoDataResponse error(TransCode transCode) {
        return new NoDataResponse(false, transCode.getCode(), transCode.getMsg());
    }

    protected DataResponse error() {
        return new DataResponse(false,null,null);
    }
    protected NoDataResponse error(TransCode transCode, String transDesc) {
        return new NoDataResponse(false, transCode.getCode(), StringUtils.defaultString(transDesc, transCode.getMsg()));
    }

}
