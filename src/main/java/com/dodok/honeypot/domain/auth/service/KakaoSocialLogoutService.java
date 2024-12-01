package com.dodok.honeypot.domain.auth.service;

import com.dodok.honeypot.domain.auth.helper.KakaoSocialHelper;
import com.dodok.honeypot.domain.auth.kakao.KakaoFeignClient;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.global.redis.entity.RefreshToken;
import com.dodok.honeypot.global.redis.helper.RefreshTokenHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class KakaoSocialLogoutService {

    @Value("${oauth2.kakao.app-admin-key}")
    private String KAKAO_APP_ADMIN_KEY;

    private final MemberHelper memberHelper;
    private final KakaoFeignClient kakaoFeignClient;
    private final KakaoSocialHelper kakaoSocialHelper;
    private final RefreshTokenHelper refreshTokenHelper;

    public void execute(Long memberId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        Long kakaoAuth = kakaoSocialHelper.findKakaoSocialByMemberOrElseThrow(member);
        //카카오서버 로그아웃
//        KakaoLogOutResDto kakaoLogOutResDto = kakaoFeignClient.logOut("KakaoAK " + KAKAO_APP_ADMIN_KEY, KakaoServerLogOutReqDto.of(kakaoAuth));
        //redis에 저장된 refreshToken 삭제
        RefreshToken refreshToken = refreshTokenHelper.findRefreshTokenOrElseThrow(memberId);
        refreshTokenHelper.deleteRefreshToken(refreshToken);
    }
}
