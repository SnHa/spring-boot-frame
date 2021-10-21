package com.atsun.coreapi.exception;

import com.atsun.coreapi.enums.TransCode;
import lombok.Getter;

@Getter
public class NoRollbackTransException extends TransException {

    private static final long serialVersionUID = -3058338134419750530L;

    public NoRollbackTransException(TransCode transCode) {
        super(transCode);
    }

    public NoRollbackTransException(TransCode transCode, String msg) {
        super(transCode, msg);
    }

    public NoRollbackTransException(TransCode transCode, String errCode, String msg) {
        super(transCode, errCode, msg);
    }

    public NoRollbackTransException(TransCode transCode, Object data) {
        super(transCode, data);
    }

    public NoRollbackTransException(TransCode transCode, String msg, Object data) {
        super(transCode, msg, data);
    }

    public NoRollbackTransException(TransCode transCode, String errCode, String msg, Object data) {
        super(transCode, errCode, msg, data);
    }

}
