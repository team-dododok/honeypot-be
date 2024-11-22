package com.dodok.honeypot.domain.auth.dto.req;

public record ReissueJwtTokenReqDto(
        String accessToken,
        String refreshToken
) {
}
