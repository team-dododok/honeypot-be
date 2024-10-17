package com.dodok.honeypot.domain.badge.dto.res;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;

import java.util.List;

/**
 * 획득한 뱃지의 정보를 반환하는 dto
 */
public record CompletedBadgeResDto(
        List<BadgeInfo> badgeInfoList
) {
}
