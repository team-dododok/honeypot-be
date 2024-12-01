package com.dodok.honeypot.domain.sendpraise.dto.res;

import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record SendPraiseInfoResDto(
        Long sendPraiseId,
        String name,
        String content,
        String stampUrl,
        String profileImageUrl,
        LocalDateTime sendDate
) {
    public static SendPraiseInfoResDto of(SendPraiseInfo sendPraiseInfo, String profileImageUrl) {
        return SendPraiseInfoResDto.builder()
                .sendPraiseId(sendPraiseInfo.sendPraiseId())
                .name(sendPraiseInfo.name())
                .content(sendPraiseInfo.content())
                .stampUrl(sendPraiseInfo.stampUrl())
                .profileImageUrl(profileImageUrl)
                .sendDate(sendPraiseInfo.sendDate())
                .build();
    }
}
