package com.dodok.honeypot.domain.badge.mapper;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
import com.dodok.honeypot.domain.badge.dto.res.CompletedBadgeResDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BadgeCompleteMapper {

    public CompletedBadgeResDto toBadgeResDto(List<BadgeInfo> badgeInfos) {
        return CompletedBadgeResDto.of(badgeInfos);

    }
}
