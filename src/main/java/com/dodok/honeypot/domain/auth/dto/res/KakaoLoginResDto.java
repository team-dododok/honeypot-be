package com.dodok.honeypot.domain.auth.dto.res;

import com.dodok.honeypot.domain.auth.dto.JwtToken;
import com.dodok.honeypot.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record KakaoLoginResDto(
        String accessToken,
        String refreshToken,
        String memberName
) {
    public static KakaoLoginResDto of(JwtToken jwtToken, Member member) {
        return KakaoLoginResDto
                .builder()
                .accessToken(jwtToken.accessToken())
                .refreshToken(jwtToken.refreshToken())
                .memberName(member.getName())
                .build();
    }
}
