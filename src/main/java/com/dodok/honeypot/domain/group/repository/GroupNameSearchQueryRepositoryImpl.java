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
        List<SearchGroupInfo> contents = queryFactory.select(Projections.constructor(SearchGroupInfo.class,
                        group.id,
                        group.name
                ))
                .from(group)
                .innerJoin(group.member, member)
                .where(startsGroupNameIgnoreCase(groupName), eqMemberId(memberId))
                .orderBy(group.createdAt.desc())
                .fetch();

        contents.sort((g1, g2) -> {
            if (g1.groupName().equalsIgnoreCase(groupName)) {
                return -1; // 정확히 일치하는 항목을 앞으로
            } else if (g2.groupName().equalsIgnoreCase(groupName)) {
                return 1; // 두 번째 항목이 정확히 일치하면 뒤로
            }
            return 0; // 그대로 유지
        });

        return contents;
    }

    private static BooleanExpression eqMemberId(Long memberId) {
        return member.id.eq(memberId);
    }

    private BooleanExpression startsGroupNameIgnoreCase(String groupName) {
        return group.name.startsWithIgnoreCase(groupName);
    }
}
