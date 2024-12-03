package com.dodok.honeypot.domain.sendpraise.entity;

public enum SendStatus {
    DIRECT,  // 일대일, 오픈일대일 - 성공
    GROUP,   // 그룹채팅방, 오픈그룹 - 성공
    MYSELF, // 나와의 채팅 - 실패
    FAIL,   // 전송실패 - 실패

}
