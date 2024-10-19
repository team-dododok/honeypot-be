package com.dodok.honeypot.domain.badge.repository;

import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;

import java.util.List;

public interface BadgeInfoQueryRepository {
    List<CompletedBadgeInfo> findAllCompletedBadgeByMemberId(Long memberId);
}
