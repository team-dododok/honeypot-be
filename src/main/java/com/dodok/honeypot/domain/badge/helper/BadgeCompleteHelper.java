package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
import com.dodok.honeypot.domain.badge.repository.BadgeCompleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BadgeCompleteHelper {

    private final BadgeCompleteRepository badgeCompleteRepository;

    /**
     * 내가 획득한 뱃지를 조회
     * @param memberId 사용자의 memberId
     * @return
     */
    public List<CompletedBadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {
        // TODO : 존재하지 않는 memberId인 경우 예외처리 하는 로직
        List<CompletedBadgeInfo> completedBadgeInfos
                = badgeCompleteRepository.findAllCompletedBadgeByMemberId(memberId);

        return completedBadgeInfos;
    }
}
