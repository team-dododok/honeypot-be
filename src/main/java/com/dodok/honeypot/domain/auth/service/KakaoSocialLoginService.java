package com.dodok.honeypot.domain.auth.service;

import com.dodok.honeypot.domain.auth.dto.JwtToken;
import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLoginResDto;
import com.dodok.honeypot.domain.auth.dto.res.UserInfoFromKakaoResDto;
import com.dodok.honeypot.domain.auth.entity.KakaoSocial;
import com.dodok.honeypot.domain.auth.helper.KakaoSocialHelper;
import com.dodok.honeypot.domain.auth.kakao.KakaoFeignClient;
import com.dodok.honeypot.domain.auth.mapper.KakaoSocialMapper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ServiceConsentHelper;
import com.dodok.honeypot.domain.member.mapper.MemberMapper;
import com.dodok.honeypot.domain.member.mapper.ServiceConsentMapper;
import com.dodok.honeypot.global.auth.JwtUtil;
import com.dodok.honeypot.global.reids.entity.RefreshToken;
import com.dodok.honeypot.global.reids.helper.RefreshTokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public KakaoLoginResDto kakaoLogin(String kakaoAccessToken, KakaoLoginReqDto requestDto) {

        Long kakaoAuth = getKakaoUserInfo(kakaoAccessToken).id();

        Optional<KakaoSocial> kakaoSocial = kakaoSocialHelper.findKakaoSocialByKakaoAuth(kakaoAuth);
        Long memberId = kakaoSocial.isEmpty() ?
                createMemberAndSaveInfo(kakaoAuth, requestDto).getId() : kakaoSocial.get().getMember().getId();

        JwtToken jwtToken = generateJwtToken(generateAccessToken(memberId, "member"), generateRefreshToken());

        deleteRefreshTokenIfExists(memberId);
        refreshTokenHelper.createRefreshTokenAndSave(memberId, jwtToken.refreshToken());

        return KakaoLoginResDto.of(jwtToken);
    }

    public void deleteRefreshTokenIfExists(Long memberId) {
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

    private UserInfoFromKakaoResDto getKakaoUserInfo(String kakaoAccessToken) {
        return kakaoFeignClient.getKakaoUserInfo(kakaoAccessToken);
    }

    private Member createMemberAndSaveInfo(Long kakaoAuth, KakaoLoginReqDto requestDto) {
        Member member = memberHelper.createMemberAndSave(requestDto);
        serviceConsentHelper.createServiceConsentAndSave(requestDto, member);
        kakaoSocialHelper.createKakaoSocialAndSave(kakaoAuth, member);
        return member;
    }
}
