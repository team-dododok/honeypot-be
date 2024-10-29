package com.dodok.honeypot.global.error.entity;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(MethodArgumentNotValidException e) {
        return ErrorResponse.builder()
                .status(e.getStatusCode().value())
                .message(getDefaultMessage(e))
                .timestamp(LocalDateTime.now())
                .build();
    }

    private static String getDefaultMessage(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return bindingResult.getFieldError().getDefaultMessage();
    }
}
