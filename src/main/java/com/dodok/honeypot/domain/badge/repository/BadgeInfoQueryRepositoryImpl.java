package com.dodok.honeypot.domain.badge.repository;

import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BadgeInfoQueryRepositoryImpl implements BadgeInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {
        return null;
    }
}
