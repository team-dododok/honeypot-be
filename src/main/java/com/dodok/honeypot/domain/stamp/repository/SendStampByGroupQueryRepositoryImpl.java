package com.dodok.honeypot.domain.stamp.repository;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dodok.honeypot.domain.group.entity.QGroup.group;
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;
import static com.dodok.honeypot.domain.stamp.entity.QHoneyStamp.honeyStamp;

@RequiredArgsConstructor
public class SendStampByGroupQueryRepositoryImpl implements SendStampByGroupQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * groupId를 기준으로 보냈던 모든 꿀도장 정보를 가져오는 레포지토리
     * @param groupId
     * @return
     */
    @Override
    public List<StampDto> findAllSendStampByGroup(Long groupId) {
        return queryFactory
                .select(Projections.constructor(
                        StampDto.class,
                        honeyStamp.imageUrl,
                        honeyStamp.stampName
                ))
                .from(honeyStamp)
                .leftJoin(sendPraise)
                .on(honeyStamp.id.eq(sendPraise.honeyStamp.id))
                .leftJoin(group)
                .on(sendPraise.group.id.eq(group.id))
                .where(group.id.eq(groupId))
                .fetch();
    }
}
