package com.dodok.honeypot.domain.receivepraise.dto;

import java.time.LocalDateTime;

public record ReceivePraiseInfo(
        Long receivePraiseId,
        String name,
        String content,
        String stampUrl,
        LocalDateTime receiveDate
) {
}
