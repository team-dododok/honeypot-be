package com.dodok.honeypot.global.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refresh_token")
@Builder
@AllArgsConstructor
public class RefreshToken {

    @Id
    private Long memberId;

    private String refreshToken;

    public static RefreshToken createRefreshToken(Long memberId, String refreshToken){
        return RefreshToken.builder()
                .memberId(memberId)
                .refreshToken(refreshToken)
                .build();
    }
}
