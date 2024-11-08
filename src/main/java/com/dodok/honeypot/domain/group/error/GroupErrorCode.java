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
     * 400 Bad Request
     */
    NOT_VALID_GROUP_ORDER_IDX(HttpStatus.BAD_REQUEST, "그룹 순서 idx가 유효하지 않습니다. 유효한 값으로 요청해주세요"),

    /**
     * 404 Not Found
     */
    GROUP_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 그룹을 찾을 수 없습니다."),
    MEMBER_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자에게서 해당 그룹을 찾을 수 없습니다."),

    /**
     * 403 Forbidden
     */
    MEMBER_NOT_GROUP_OWNER_FORBIDDEN(HttpStatus.FORBIDDEN, "해당 그룹들에 대한 사용자의 권한이 없습니다."),

    /**
     * 409 Conflict
     */
    DUPLICATE_GROUP_NAME(HttpStatus.CONFLICT, "이미 해당 그룹명이 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
