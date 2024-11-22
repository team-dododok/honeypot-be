package com.dodok.honeypot.domain.auth.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRole {
    MEMBER("member");

    private final String role;
}
