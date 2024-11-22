package com.dodok.honeypot.domain.sendpraise.repository;

import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
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
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;
import static com.dodok.honeypot.domain.stamp.entity.QHoneyStamp.honeyStamp;

@RequiredArgsConstructor
public class GroupSendPraiseGetInfoQueryRepositoryImpl implements GroupSendPraiseGetInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SendPraiseInfo> findSendPraiseInfosByGroupId(Long groupId, Pageable pageable) {
        List<SendPraiseInfo> contents = queryFactory.
                select(Projections.constructor(SendPraiseInfo.class,
                        sendPraise.id,
                        sendPraise.receiverName,
                        sendPraise.content,
                        honeyStamp.imageUrl,
                        sendPraise.createdAt
                ))
                .from(sendPraise)
                .join(sendPraise.group, group)
                .join(sendPraise.honeyStamp, honeyStamp)
                .where(eqGroupId(groupId))
                .orderBy(CreatedAtDesc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(sendPraise.id.countDistinct())
                .from(sendPraise)
                .join(sendPraise.group, group)
                .where(eqGroupId(groupId));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private static OrderSpecifier<LocalDateTime> CreatedAtDesc() {
        return sendPraise.createdAt.desc();
    }

    private BooleanExpression eqGroupId(Long groupId) {
        return group.id.eq(groupId);
    }
}
