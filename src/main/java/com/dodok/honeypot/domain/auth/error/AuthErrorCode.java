package com.dodok.honeypot.domain.auth.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthErrorCode implements ErrorCode {

    /**
     * 404 Not Found
     */
    KAKAO_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "카카오로 회원가입 된 유저를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
