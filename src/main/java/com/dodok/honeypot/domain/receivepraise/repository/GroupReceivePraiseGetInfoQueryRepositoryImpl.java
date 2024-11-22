package com.dodok.honeypot.domain.receivepraise.repository;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.dodok.honeypot.domain.group.entity.QGroup.group;
import static com.dodok.honeypot.domain.member.entity.QMember.member;
import static com.dodok.honeypot.domain.receivepraise.entity.QReceivePraise.receivePraise;
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;
import static com.dodok.honeypot.domain.stamp.entity.QHoneyStamp.honeyStamp;

@RequiredArgsConstructor
public class GroupReceivePraiseGetInfoQueryRepositoryImpl implements GroupReceivePraiseGetInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReceivePraiseInfo> findReceivePraiseInfosByGroupId(Long groupId, Pageable pageable) {
        List<ReceivePraiseInfo> contents = queryFactory.
                select(Projections.constructor(ReceivePraiseInfo.class,
                        receivePraise.id,
                        member.name,
                        honeyStamp.imageUrl,
                        receivePraise.createdAt
                ))
                .from(receivePraise)
                .join(receivePraise.group, group)
                .join(receivePraise.sendPraise, sendPraise)
                .join(sendPraise.sender, member)
                .join(sendPraise.honeyStamp, honeyStamp)
                .where(eqGroupId(groupId))
                .orderBy(CreatedAtDesc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(receivePraise.id.countDistinct())
                .from(receivePraise)
                .join(receivePraise.group, group)
                .where(eqGroupId(groupId));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private static OrderSpecifier<LocalDateTime> CreatedAtDesc() {
        return receivePraise.createdAt.desc();
    }

    private BooleanExpression eqGroupId(Long groupId) {
        return group.id.eq(groupId);
    }
}
