package com.dodok.honeypot.domain.badge.repository;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BadgeInfoQueryRepository {
    List<BadgeInfo> findAllCompletedBadgeByMemberId(Long memberId);
}
