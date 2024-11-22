package com.dodok.honeypot.global.redis.helper;

import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import com.dodok.honeypot.global.redis.entity.RefreshToken;
import com.dodok.honeypot.global.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.dodok.honeypot.domain.auth.error.AuthErrorCode.REFRESH_TOKEN_NOT_FOUND;
import static com.dodok.honeypot.global.redis.entity.RefreshToken.createRefreshToken;

@Component
@RequiredArgsConstructor
public class RefreshTokenHelper {
    private final RefreshTokenRepository refreshTokenRepository;
    public void createRefreshTokenAndSave(Long memberId, String refreshToken) {
        refreshTokenRepository.save(createRefreshToken(memberId, refreshToken));
    }

    public Optional<RefreshToken> findRefreshToken(Long memberId) {
        return refreshTokenRepository.findById(memberId);
    }

    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    public RefreshToken findRefreshTokenOrElseThrow(Long memberId) {
        return refreshTokenRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(REFRESH_TOKEN_NOT_FOUND));
    }
}
