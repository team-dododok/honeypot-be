package com.dodok.honeypot.domain.group.error;

import com.dodok.honeypot.global.error.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum GroupErrorCode implements ErrorCode {

    /**
     * 404 Not Found
     */
    GROUP_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 그룹을 찾을 수 없습니다."),
    MEMBER_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자에게서 해당 그룹을 찾을 수 없습니다."),

    /**
     * 409 Conflict
     */
    DUPLICATE_GROUP_NAME(HttpStatus.CONFLICT, "이미 해당 그룹명이 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
