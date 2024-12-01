package com.dodok.honeypot.domain.auth.dto.req;

public record KakaoLogoutReqDto(
        String accessToken,
        String refreshToken
) {
}
