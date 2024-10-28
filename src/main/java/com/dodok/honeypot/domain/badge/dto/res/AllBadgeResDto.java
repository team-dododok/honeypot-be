package com.dodok.honeypot.domain.badge.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

/**
 * 획득한 뱃지의 정보를 반환하는 dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record AllBadgeResDto(
        List<BadgeResDto> allBadgeInfos
) {
    public static AllBadgeResDto of(List<BadgeResDto> badgeResDtos){
        return AllBadgeResDto.builder()
                .allBadgeInfos(badgeResDtos)
                .build();
    }
}
