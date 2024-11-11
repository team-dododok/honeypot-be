package com.dodok.honeypot.domain.receivepraise.repository;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

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
    public Slice<ReceivePraiseInfo> findReceivePraiseInfosByGroupId(Long groupId, Pageable pageable) {
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
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(contents, pageable, hasNextPage(contents, pageable.getPageSize()));

    }

    private static OrderSpecifier<LocalDateTime> CreatedAtDesc() {
        return receivePraise.createdAt.desc();
    }

    private boolean hasNextPage(List<ReceivePraiseInfo> receivePraiseInfos, int pageSize) {
        if (receivePraiseInfos.size() > pageSize) {
            receivePraiseInfos.remove(pageSize);
            return true;
        }
        return false;
    }

    private BooleanExpression eqGroupId(Long groupId) {
        return group.id.eq(groupId);
    }
}
