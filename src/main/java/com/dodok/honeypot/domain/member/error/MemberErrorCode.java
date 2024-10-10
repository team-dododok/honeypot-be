package com.dodok.honeypot.domain.member.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MemberErrorCode implements ErrorCode {

    /**
     * 404 Not Found
     */
    MEMBER_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
