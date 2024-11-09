package com.dodok.honeypot.domain.sendpraise.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SendPraiseResDto(
        String uuid
) {
    public static SendPraiseResDto of(String uuid){
        return SendPraiseResDto.builder()
                .uuid(uuid)
                .build();
    }
}
