package com.dodok.honeypot.domain.member.dto.res;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberInfoResDto(
        MemberInfo memberInfo
){
    public static MemberInfoResDto of(MemberInfo memberInfo) {
        return MemberInfoResDto.builder()
                .memberInfo(memberInfo)
                .build();
    }
}
