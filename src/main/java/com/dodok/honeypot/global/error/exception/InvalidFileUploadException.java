package com.dodok.honeypot.global.error.exception;


import com.dodok.honeypot.global.error.code.ErrorCode;
import com.dodok.honeypot.global.error.code.GlobalErrorCode;

public class InvalidFileUploadException extends BusinessException {
    public InvalidFileUploadException() {
        super(GlobalErrorCode.INVALID_FILE_UPLOAD);
    }

    public InvalidFileUploadException(ErrorCode errorCode) {
        super(errorCode);
    }
}

