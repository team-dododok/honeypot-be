package com.dodok.honeypot.domain.member.dto.info;

public record MemberInfo(
    Long memberId,
    String name,
    String email,
    String imageUrl
) {
}
