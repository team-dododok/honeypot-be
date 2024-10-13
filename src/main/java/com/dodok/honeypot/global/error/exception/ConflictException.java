package com.dodok.honeypot.global.error.exception;


import com.dodok.honeypot.global.error.code.ErrorCode;
import com.dodok.honeypot.global.error.code.GlobalErrorCode;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(GlobalErrorCode.CONFLICT);
    }

    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}


