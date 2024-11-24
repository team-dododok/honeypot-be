package com.dodok.honeypot.domain.auth.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import com.sun.net.httpserver.HttpsServer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthErrorCode implements ErrorCode {


    /**
     * 400 BADREQUEST
     */
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST,"헤더에 AccessToken이 존재하지 않습니다."),

    /**
     * 401 UNAUTHORIZED
     */
    REFRESH_TOKEN_NOT_MATCH(HttpStatus.UNAUTHORIZED,"해당 유저 ID와 일치하지 않는 REFRESH TOKEN 입니다."),
    TOKEN_TYPE_NOT_MATCH(HttpStatus.UNAUTHORIZED,"토큰 타입이 일치하지 않습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"Access Token이 만료되었습니다. 재발급 받아주세요."),
    ACCESS_TOKEN_INVALID(HttpStatus.UNAUTHORIZED,"유효하지 않은 AccessToken입니다."),
    JWT_TOKEN_MALFORMED(HttpStatus.UNAUTHORIZED,"JWT 토큰의 형식이 올바르지 않습니다."),
    JWT_TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED,"지원하지 않는 JWT 토큰입니다."),
    JWT_TOKEN_MISSING(HttpStatus.UNAUTHORIZED,"JWT 토큰이 없거나 비어 있습니다."),

    /**
     * 404 Not Found
     */
    KAKAO_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "카카오로 회원가입 된 유저를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "유저와 일치하는 엑세스 토큰을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
