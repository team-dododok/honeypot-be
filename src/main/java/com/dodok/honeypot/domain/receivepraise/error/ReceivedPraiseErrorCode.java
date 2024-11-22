package com.dodok.honeypot.domain.receivepraise.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReceivedPraiseErrorCode implements ErrorCode {

    /**
     * 404 Not Found
     */
    RECEIVED_PRAISE_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "받은칭찬을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
