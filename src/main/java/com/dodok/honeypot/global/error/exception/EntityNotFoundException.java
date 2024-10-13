package com.dodok.honeypot.global.error.exception;


import com.dodok.honeypot.global.error.code.ErrorCode;
import com.dodok.honeypot.global.error.code.GlobalErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(GlobalErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

