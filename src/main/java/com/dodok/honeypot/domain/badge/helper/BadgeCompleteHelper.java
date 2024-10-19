package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
import com.dodok.honeypot.domain.badge.repository.BadgeCompleteRepository;
import com.dodok.honeypot.domain.member.error.MemberErrorCode;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BadgeCompleteHelper {

    private final BadgeCompleteRepository badgeCompleteRepository;

    /**
     * 전체 뱃지 + 내가 획득한 뱃지를 조회
     * @param memberId 사용자의 memberId
     * @return
     */
    public List<CompletedBadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {
        List<CompletedBadgeInfo> completedBadgeInfos
                = badgeCompleteRepository.findAllCompletedBadgeByMemberId(memberId);
        if(completedBadgeInfos.isEmpty()){
            throw new EntityNotFoundException(MemberErrorCode.MEMBER_ENTITY_NOT_FOUND);
        }
        return completedBadgeInfos;
    }
}
