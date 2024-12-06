package com.dodok.honeypot.domain.praise.dto.info;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberPraiseInfo(
        Long receivePraiseCount,
        Long sendPraiseCount,
        String bestStampUrl
) {
    public static MemberPraiseInfo of(Long receivePraiseCount, Long sendPraiseCount, String bestStampUrl) {
        return MemberPraiseInfo.builder()
                .receivePraiseCount(receivePraiseCount)
                .sendPraiseCount(sendPraiseCount)
                .bestStampUrl(bestStampUrl)
                .build();
    }
}
