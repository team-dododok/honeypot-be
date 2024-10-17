package com.dodok.honeypot.domain.badge.dto.res;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

/**
 * 획득한 뱃지의 정보를 반환하는 dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record CompletedBadgeResDto(
        List<BadgeInfo> badgeInfoList
) {
    public static CompletedBadgeResDto of(List<BadgeInfo> badgeInfoList){
        return CompletedBadgeResDto.builder()
                .badgeInfoList(badgeInfoList)
                .build();
    }
}
