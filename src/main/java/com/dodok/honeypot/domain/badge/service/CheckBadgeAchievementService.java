package com.dodok.honeypot.domain.badge.service;

import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
import com.dodok.honeypot.domain.badge.dto.res.AllBadgeResDto;
import com.dodok.honeypot.domain.badge.helper.BadgeCompleteHelper;
import com.dodok.honeypot.domain.badge.helper.BadgeHelper;
import com.dodok.honeypot.domain.badge.mapper.BadgeCompleteMapper;
import com.dodok.honeypot.domain.badge.mapper.BadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 로직이 수행될 때 마다, 뱃지 획득 조건을 달성했는지 체크하는 서비스 로직
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckBadgeAchievementService {
    private final BadgeMapper badgeMapper;
    private final BadgeCompleteMapper badgeCompleteMapper;

    private final BadgeHelper badgeHelper;
    private final BadgeCompleteHelper badgeCompleteHelper;

    public AllBadgeResDto execute(Long memberId){
        List<AllBadgeInfo> allBadgeInfo = badgeHelper.getAllBadge();
        List<CompletedBadgeInfo> completedBadgeInfos = badgeCompleteHelper.findAllCompletedBadgeByMemberId(memberId);
        return badgeCompleteMapper.toBadgeResDto(allBadgeInfo,completedBadgeInfos);
    }
}
