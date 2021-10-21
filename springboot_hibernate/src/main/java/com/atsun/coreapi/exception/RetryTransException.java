package com.atsun.coreapi.exception;

import com.atsun.coreapi.enums.TransCode;
import lombok.Getter;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/2/26</p>
 *
 * @author LD
 */
@Getter
public class RetryTransException extends TransException {

    private static final long serialVersionUID = -8772525214878139981L;

    public RetryTransException(TransCode transCode) {
        super(transCode);
    }

    public RetryTransException(TransCode transCode, String msg) {
        super(transCode, msg);
    }

    public RetryTransException(TransCode transCode, String errCode, String msg) {
        super(transCode, errCode, msg);
    }

    public RetryTransException(TransCode transCode, Object data) {
        super(transCode, data);
    }

    public RetryTransException(TransCode transCode, String msg, Object data) {
        super(transCode, msg, data);
    }

    public RetryTransException(TransCode transCode, String errCode, String msg, Object data) {
        super(transCode, errCode, msg, data);
    }

}
