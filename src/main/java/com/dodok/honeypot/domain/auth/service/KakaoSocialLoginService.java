package com.dodok.honeypot.domain.auth.service;

import com.dodok.honeypot.domain.auth.dto.JwtToken;
import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLoginResDto;
import com.dodok.honeypot.domain.auth.helper.KakaoSocialHelper;
import com.dodok.honeypot.domain.auth.kakao.KakaoFeignClient;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ServiceConsentHelper;
import com.dodok.honeypot.global.auth.JwtUtil;
import com.dodok.honeypot.global.redis.helper.RefreshTokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.dodok.honeypot.domain.auth.type.MemberRole.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KakaoSocialLoginService {

    private final KakaoSocialHelper kakaoSocialHelper;
    private final MemberHelper memberHelper;
    private final ServiceConsentHelper serviceConsentHelper;
    private final RefreshTokenHelper refreshTokenHelper;
    private final KakaoFeignClient kakaoFeignClient;
    private final JwtUtil jwtUtil;

    public KakaoLoginResDto kakaoLogin(String kakaoAccessToken) {
        Long memberId = kakaoSocialHelper.findKakaoSocialByKakaoAuthOrElseThrow(getKakaoMemberId(kakaoAccessToken));
        return KakaoLoginResDto.of(createJwtToken(memberId));
    }


    public KakaoLoginResDto register(String kakaoAccessToken, KakaoLoginReqDto requestDto) {
        Long memberId = createMemberAndSaveInfo(getKakaoMemberId(kakaoAccessToken), requestDto).getId();
        return KakaoLoginResDto.of(createJwtToken(memberId));
    }

    private JwtToken createJwtToken(Long memberId) {
        JwtToken jwtToken = generateJwtToken(generateAccessToken(memberId, MEMBER.getRole()), generateRefreshToken());
        deleteRefreshTokenIfExists(memberId);
        refreshTokenHelper.createRefreshTokenAndSave(memberId, jwtToken.refreshToken());
        return jwtToken;
    }

    private void deleteRefreshTokenIfExists(Long memberId) {
        refreshTokenHelper.findRefreshToken(memberId).ifPresent(refreshTokenHelper::deleteRefreshToken);
    }

    private JwtToken generateJwtToken(String accessToken, String refreshToken) {
        return JwtToken.of(accessToken, refreshToken);
    }

    private String generateAccessToken(Long id, String role) {
        return jwtUtil.createAccessToken(id, role);
    }

    private String generateRefreshToken() {
        return jwtUtil.createRefreshToken();
    }

    private Long getKakaoMemberId(String kakaoAccessToken) {
        return kakaoFeignClient.getKakaoUserInfo(kakaoAccessToken).id();
    }

    private Member createMemberAndSaveInfo(Long kakaoAuth, KakaoLoginReqDto requestDto) {
        Member member = memberHelper.createMemberAndSave(requestDto);
        serviceConsentHelper.createServiceConsentAndSave(requestDto, member);
        kakaoSocialHelper.createKakaoSocialAndSave(kakaoAuth, member);
        return member;
    }
}
