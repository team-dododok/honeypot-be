package com.dodok.honeypot.domain.auth.dto.req;

import lombok.Builder;


@Builder
public record KakaoServerLogOutReqDto(
        String target_id_type,
        Long target_id
) {

    public static KakaoServerLogOutReqDto of(Long targetId){
        return KakaoServerLogOutReqDto.builder()
                .target_id_type("user_id")
                .target_id(targetId)
                .build();
    }

}
