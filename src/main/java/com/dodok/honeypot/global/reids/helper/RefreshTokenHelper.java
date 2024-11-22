package com.dodok.honeypot.global.reids.helper;

import com.dodok.honeypot.global.reids.entity.RefreshToken;
import com.dodok.honeypot.global.reids.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.dodok.honeypot.global.reids.entity.RefreshToken.createRefreshToken;

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
}
