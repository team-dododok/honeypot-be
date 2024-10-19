package com.dodok.honeypot.domain.badge.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * 뱃지의 정보를 저장하는 dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record BadgeInfo(
        String name,
        String description,
        String imageUrl,
        LocalDateTime completeDate
) {

    public BadgeInfo of(String name, String description, String imageUrl, LocalDateTime completeDate){
        return BadgeInfo.builder()
                .name(name)
                .description(description)
                .imageUrl(imageUrl)
                .completeDate(completeDate)
                .build();
    }
}
