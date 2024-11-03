package com.dodok.honeypot.domain.auth.dto.res;

import com.dodok.honeypot.domain.auth.dto.JwtToken;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record KakaoLoginResDto(
        String accessToken,
        String refreshToken
) {
    public static KakaoLoginResDto of(JwtToken jwtToken) {
        return KakaoLoginResDto
                .builder()
                .accessToken(jwtToken.accessToken())
                .refreshToken(jwtToken.refreshToken())
                .build();
    }
}
