package com.dodok.honeypot.domain.badge.dto;

/**
 * 모든 뱃지의 정보를 저장하는 dto
 */
public record AllBadgeInfo(
        Long id,
        String imageUrl,
        String name,
        String description

) {
}
