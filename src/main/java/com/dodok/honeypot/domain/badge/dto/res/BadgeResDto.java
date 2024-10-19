package com.dodok.honeypot.domain.badge.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * 뱃지의 정보를 저장하는 dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record BadgeResDto(
        String name,
        String description,
        String imageUrl,

        LocalDateTime completedDate
) {
    public static BadgeResDto of(String name, String description, String imageUrl,LocalDateTime completedDate) {
        return BadgeResDto.builder()
                .name(name)
                .description(description)
                .imageUrl(imageUrl)
                .completedDate(completedDate)
                .build();
    }
}
