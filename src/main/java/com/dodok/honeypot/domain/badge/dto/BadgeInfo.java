package com.dodok.honeypot.domain.badge.dto;

import java.time.LocalDateTime;

/**
 * 뱃지의 정보를 저장하는 dto
 */
public record BadgeInfo(
        String name,
        String description,
        String image_url,
        LocalDateTime completeDate
) {
}
