package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dodok.honeypot.domain.group.entity.QGroup.group;
import static com.dodok.honeypot.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class GroupNameSearchQueryRepositoryImpl implements GroupNameSearchQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SearchGroupInfo> findAllByNameContainsAndMember(Long memberId, String groupName) {
        return queryFactory.select(Projections.constructor(SearchGroupInfo.class,
                        group.id,
                        group.name
                ))
                .from(group)
                .innerJoin(group.member, member)
                .where(containsGroupNameIgnoreCase(groupName), eqMemberId(memberId))
                .orderBy(group.createdAt.desc())
                .fetch();
    }

    private static BooleanExpression eqMemberId(Long memberId) {
        return member.id.eq(memberId);
    }

    private BooleanExpression containsGroupNameIgnoreCase(String groupName) {
        return group.name.containsIgnoreCase(groupName);
    }
}
