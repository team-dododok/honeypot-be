package com.dodok.honeypot.domain.praise.dto.info;

public record MemberPraiseInfo(
        Long receivePraiseCount,
        Long sendPraiseCount,
        String bestStampUrl
) {
}
