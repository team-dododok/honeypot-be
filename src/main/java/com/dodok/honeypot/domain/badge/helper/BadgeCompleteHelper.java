package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
import com.dodok.honeypot.domain.badge.repository.BadgeCompleteRepository;
import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BadgeCompleteHelper {

    private final BadgeCompleteRepository badgeCompleteRepository;

    public List<BadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {
        return badgeCompleteRepository.findAllCompletedBadgeByMemberId(memberId);
    }
}
