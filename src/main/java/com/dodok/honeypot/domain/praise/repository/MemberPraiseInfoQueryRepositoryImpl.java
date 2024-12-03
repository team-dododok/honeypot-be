package com.dodok.honeypot.domain.praise.repository;

import com.dodok.honeypot.domain.praise.dto.info.MemberPraiseInfo;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.dodok.honeypot.domain.member.entity.QMember.member;
import static com.dodok.honeypot.domain.receivepraise.entity.QReceivePraise.receivePraise;
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;
import static com.dodok.honeypot.domain.stamp.entity.QHoneyStamp.honeyStamp;

@RequiredArgsConstructor
public class MemberPraiseInfoQueryRepositoryImpl implements MemberPraiseInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public MemberPraiseInfo findMemberPraiseInfosByMemberIdAndSendStatusIsTrue(Long memberId) {
        return queryFactory.
                select(Projections.constructor(MemberPraiseInfo.class,
                        receivePraise.id.countDistinct(),
                        sendPraise.id.countDistinct(),
                        honeyStamp.imageUrl.max()
                ))
                .from(member)
                .leftJoin(receivePraise).on(receivePraise.receiver.eq(member))
                .leftJoin(sendPraise).on(sendPraise.sender.eq(member))
                .leftJoin(receivePraise.sendPraise.honeyStamp, honeyStamp)
                .where(eqMemberId(memberId),eqSendPraiseStatusIsTrue())
                .groupBy(member.id)
                .fetchOne();
    }

    private BooleanExpression eqMemberId(Long memberId) {
        return member.id.eq(memberId);
    }
    private BooleanExpression eqSendPraiseStatusIsTrue() {
        return sendPraise.sendStatus.in(SendStatus.DIRECT, SendStatus.GROUP);
    }
}
