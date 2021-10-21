package com.atsun.coreapi.bean;

import com.atsun.coreapi.enums.TransCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Description: Created by LD on 2020/02/01</p>
 *
 * @author LD
 */
@Getter
@Setter
public class NoDataResponse implements Serializable {

    private static final long serialVersionUID = 8832460930434550146L;

    private boolean success;

    private String transCode;

    private String transDesc;

    private String errCode;

    private String errDesc;

    public NoDataResponse(boolean success, String transCode, String transDesc, String errCode, String errDesc) {
        this.success = success;
        this.transCode = success && StringUtils.isBlank(transCode) ? TransCode.SUCCESS.getCode() : transCode;
        this.transDesc = success && StringUtils.isBlank(transDesc) ? TransCode.SUCCESS.getMsg() : transDesc;
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public NoDataResponse(boolean success, String transCode, String transDesc) {
        this(success, transCode, transDesc, null, null);
    }

    public NoDataResponse() {
        this(true, null, null, null, null);
    }

}
