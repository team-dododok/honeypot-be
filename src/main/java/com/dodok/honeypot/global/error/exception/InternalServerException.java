package com.dodok.honeypot.global.error.exception;


import com.dodok.honeypot.global.error.code.ErrorCode;
import com.dodok.honeypot.global.error.code.GlobalErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException() {
        super(GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}

