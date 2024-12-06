package com.dodok.honeypot.domain.stamp.repository;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dodok.honeypot.domain.receivepraise.entity.QReceivePraise.receivePraise;
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;
import static com.dodok.honeypot.domain.stamp.entity.QHoneyStamp.honeyStamp;

@RequiredArgsConstructor
public class ReceivedStampByMemberQueryRepositoryImpl implements ReceivedStampByMemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * groupId를 기준으로 받은 모든 꿀도장 정보를 가져오는 레포지토리
     * @param memberId 그룹 id
     * @return 받은 꿀도장의 전체 정보
     */
    @Override
    public List<StampDto> findAllReceivedStampByMember(Long memberId) {
        return queryFactory
                .select(Projections.constructor(
                        StampDto.class,
                        honeyStamp.id,
                        honeyStamp.imageUrl,
                        honeyStamp.stampName
                ))
                .from(honeyStamp)
                .leftJoin(sendPraise)
                .on(honeyStamp.id.eq(sendPraise.honeyStamp.id))
                .leftJoin(receivePraise)
                .on(receivePraise.sendPraise.id.eq(sendPraise.id))
                .where(receivePraise.receiver.id.eq(memberId))
                .fetch();
    }
}
