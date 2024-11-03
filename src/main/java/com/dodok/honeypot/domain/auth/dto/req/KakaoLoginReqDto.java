package com.dodok.honeypot.domain.auth.dto.req;

/**
 * 회원가입 시 사용자 정보를 담은 Dto
 */
public record KakaoLoginReqDto(
        boolean serviceTerm, //서비스 이용약관 동의
        boolean personalInfo, //개인정보 수집 및 이용 동의
        boolean emailMarketing, //E-mail 광고성 정보 수신동의
        String name,
        String email,
        String imageUrl,
        boolean onboarding
) {
}
