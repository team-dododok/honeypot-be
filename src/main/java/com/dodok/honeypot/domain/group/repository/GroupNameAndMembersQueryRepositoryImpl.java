package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupInfo;
import com.dodok.honeypot.domain.group.dto.GroupMemberNameInfo;
import com.dodok.honeypot.domain.group.dto.GroupMembersInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dodok.honeypot.domain.group.entity.QGroup.group;
import static com.dodok.honeypot.domain.receivepraise.entity.QReceivePraise.receivePraise;
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;

@RequiredArgsConstructor
public class GroupNameAndMembersQueryRepositoryImpl implements GroupNameAndMembersQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupMembersInfo> findGroupNameAndMembersAndDeletedAtIsNull(Long memberId, List<Long> groupIds) {
        List<GroupInfo> groupInfos = new ArrayList<>();
        groupInfos.addAll(queryFactory
                .select(Projections.constructor(GroupInfo.class,
                        group.id,
                        group.name,
                        sendPraise.receiverName,
                        group.orderIdx
                ))
                .from(group)
                .leftJoin(group.sendPraises, sendPraise)
                .where(eqMemberId(memberId), inGroupIds(groupIds),eqGroupDeleteAtIsNull())
                .orderBy(group.orderIdx.asc())
                .fetch()
        );

        groupInfos.addAll(queryFactory
                .select(Projections.constructor(GroupInfo.class,
                        group.id,
                        group.name,
                        receivePraise.sendPraise.sender.name,
                        group.orderIdx
                ))
                .from(group)
                .leftJoin(group.receivePraises, receivePraise)
                .where(eqMemberId(memberId), inGroupIds(groupIds),eqGroupDeleteAtIsNull())
                .orderBy(group.orderIdx.asc())
                .fetch()
        );

        return new ArrayList<>(groupInfos.stream()
                .collect(Collectors.groupingBy(
                        GroupInfo::groupId,     // 그룹 id를 통해 Grouping
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                groupInfo -> {
                                    return GroupMembersInfo.of(   // 그룹 id를 통해 묶은 값들 중에 Id, Name, OrderIdx는 공통이니 하나만 뽑고 memberName은 distinct로 중복 허용하지 않고 가져오기
                                            groupInfo.get(0).groupId(),
                                            groupInfo.get(0).groupName(),
                                            groupInfo.get(0).orderIdx(),
                                            groupInfo.stream()
                                                    .map(GroupInfo::memberName)
                                                    .filter(Objects::nonNull) // null 값을 필터링
                                                    .distinct()
                                                    .map(GroupMemberNameInfo::of)
                                                    .collect(Collectors.toList())
                                    );
                                }
                        )
                ))
                .values());
    }

    private static BooleanExpression inGroupIds(List<Long> groupIds) {
        return group.id.in(groupIds);
    }

    private BooleanExpression eqMemberId(Long memberId) {
        return group.member.id.eq(memberId);
    }
    private BooleanExpression eqGroupDeleteAtIsNull() {
        return group.deletedAt.isNull();
    }
}
