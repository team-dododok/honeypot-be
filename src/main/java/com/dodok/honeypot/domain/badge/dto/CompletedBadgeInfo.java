package com.dodok.honeypot.domain.badge.dto;

import java.time.LocalDateTime;

/**
 * 획득한 뱃지의 정보를 저장하는 dto
 */
public record CompletedBadgeInfo(
        Long badgeId,
        LocalDateTime completedDate
) {
}
