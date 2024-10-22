package com.dodok.honeypot.domain.member.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProfileImageErrorCode implements ErrorCode {

    /**
     * 404 Not Found
     */
    PROFILE_IMAGE_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 프로필 이미지를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
