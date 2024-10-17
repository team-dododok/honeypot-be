package com.dodok.honeypot.domain.badge.service;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
import com.dodok.honeypot.domain.badge.dto.req.CompletedBadgeReqDto;
import com.dodok.honeypot.domain.badge.dto.res.CompletedBadgeResDto;
import com.dodok.honeypot.domain.badge.helper.BadgeCompleteHelper;
import com.dodok.honeypot.domain.badge.helper.BadgeHelper;
import com.dodok.honeypot.domain.badge.mapper.BadgeCompleteMapper;
import com.dodok.honeypot.domain.badge.mapper.BadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자가 획득한 뱃지의 정보를 조회하는 서비스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetBadgeInfoService {
    private final BadgeMapper badgeMapper;
    private final BadgeCompleteMapper badgeCompleteMapper;

    private final BadgeHelper badgeHelper;
    private final BadgeCompleteHelper badgeCompleteHelper;

    public CompletedBadgeResDto execute(CompletedBadgeReqDto req){
        List<BadgeInfo> badgeInfos = badgeCompleteHelper.findAllCompletedBadgeByMemberId(req.memberId());
        return badgeCompleteMapper.toBadgeResDto(badgeInfos);
    }
}
