package com.dodok.honeypot.domain.badge.mapper;

import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
import com.dodok.honeypot.domain.badge.dto.res.AllBadgeResDto;
import com.dodok.honeypot.domain.badge.dto.res.BadgeResDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BadgeCompleteMapper {

    public AllBadgeResDto toBadgeResDto(List<AllBadgeInfo> allBadgeInfos, List<CompletedBadgeInfo> completedBadgeInfos) {
        // completedBadge의 {id, completedTime}을 저장하는 해시
        Map<Long, LocalDateTime> completedBadgeMap = completedBadgeInfos.stream()
                .collect(Collectors.toMap(CompletedBadgeInfo::badgeId, CompletedBadgeInfo::completedDate));


        List<BadgeResDto> badgeResDtos = new ArrayList<>();
        allBadgeInfos.forEach(allBadgeInfo -> {
            // 뱃지를 획득한 날짜
            LocalDateTime completedDate = completedBadgeMap.get(allBadgeInfo.id());

            BadgeResDto badgeResDto = ( completedDate==null)
                    ? BadgeResDto.of(allBadgeInfo.name(), allBadgeInfo.description(), allBadgeInfo.imageUrl(),null)
                    : BadgeResDto.of(allBadgeInfo.name(), allBadgeInfo.description(), allBadgeInfo.imageUrl(),completedDate);

            badgeResDtos.add(badgeResDto);
        });
        return AllBadgeResDto.of(badgeResDtos);

    }
}
