package com.dodok.honeypot.domain.sendpraise.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum SendPraiseErrorCode implements ErrorCode {

    /**
     * 404 Not Found
     */
    SEND_PRAISE_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "보낸 칭찬을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
