package com.dodok.honeypot.domain.auth.dto.res;

import com.dodok.honeypot.domain.auth.dto.JwtToken;
import lombok.Builder;

@Builder
public record ReissueJwtTokenResDto(
        String accessToken,
        String refreshToken
) {
    public static ReissueJwtTokenResDto of(JwtToken jwtToken){
        return ReissueJwtTokenResDto.builder()
                .accessToken(jwtToken.accessToken())
                .refreshToken(jwtToken.refreshToken())
                .build();
    }
}

