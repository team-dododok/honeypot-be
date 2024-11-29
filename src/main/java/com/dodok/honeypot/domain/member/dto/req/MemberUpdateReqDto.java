package com.dodok.honeypot.domain.member.dto.req;

public record MemberUpdateReqDto (
        String name,
        String profileImageUrl,
        String email
){
}
