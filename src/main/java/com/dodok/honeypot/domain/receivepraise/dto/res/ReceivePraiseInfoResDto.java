package com.dodok.honeypot.domain.receivepraise.dto.res;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ReceivePraiseInfoResDto(
        Long receivePraiseId,
        String name,
        String content,
        String stampUrl,
        String profileImageUrl,
        LocalDateTime receiveDate
) {
    public static ReceivePraiseInfoResDto of(ReceivePraiseInfo receivePraiseInfo, String profileImageUrl) {
        return ReceivePraiseInfoResDto.builder()
                .receivePraiseId(receivePraiseInfo.receivePraiseId())
                .name(receivePraiseInfo.name())
                .content(receivePraiseInfo.content())
                .stampUrl(receivePraiseInfo.stampUrl())
                .profileImageUrl(profileImageUrl)
                .receiveDate(receivePraiseInfo.receiveDate())
                .build();
    }
}
