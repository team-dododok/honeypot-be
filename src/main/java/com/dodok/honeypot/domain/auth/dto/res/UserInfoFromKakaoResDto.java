package com.dodok.honeypot.domain.auth.dto.res;

/**
 * 카카오로부터 전달받은 사용자 정보를 저장하는 dto
 */
public record UserInfoFromKakaoResDto(
        Long id
) {
}
