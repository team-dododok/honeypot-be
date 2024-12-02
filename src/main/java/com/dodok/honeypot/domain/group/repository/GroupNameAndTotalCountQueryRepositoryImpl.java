package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupNamePraiseCountInfo;
import com.dodok.honeypot.domain.member.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.dodok.honeypot.domain.group.entity.QGroup.group;

@RequiredArgsConstructor
public class GroupNameAndTotalCountQueryRepositoryImpl implements GroupNameAndTotalCountQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<GroupNamePraiseCountInfo> findNameAndPraiseCountAndDeletedAtIsNull(Long groupId, Member member) {
        return Optional.ofNullable(queryFactory.select(Projections.constructor(GroupNamePraiseCountInfo.class,
                        group.id,
                        group.name,
                        group.receivePraises.size(),
                        group.sendPraises.size()

                ))
                .from(group)
                .where(eqGroupId(groupId), eqMember(member), eqGroupDeleteAtIsNull())
                .fetchOne());
    }

    private BooleanExpression eqMember(Member member) {
        return group.member.eq(member);
    }

    private BooleanExpression eqGroupId(Long groupId) {
        return group.id.eq(groupId);
    }

    private BooleanExpression eqGroupDeleteAtIsNull() {
        return group.deletedAt.isNull();
    }
}
