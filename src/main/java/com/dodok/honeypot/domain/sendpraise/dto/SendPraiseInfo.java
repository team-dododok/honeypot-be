package com.dodok.honeypot.domain.sendpraise.dto;

import java.time.LocalDateTime;

public record SendPraiseInfo(
        Long sendPraiseId,
        String name,
        String content,
        String stampUrl,
        LocalDateTime sendDate
) {
}
