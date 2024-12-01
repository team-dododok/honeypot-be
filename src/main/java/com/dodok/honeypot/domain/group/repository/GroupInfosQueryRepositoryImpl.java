package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupInfo;
import com.dodok.honeypot.domain.group.dto.GroupMemberNameInfo;
import com.dodok.honeypot.domain.group.dto.GroupPraiseCountInfo;
import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static com.dodok.honeypot.domain.group.entity.QGroup.group;
import static com.dodok.honeypot.domain.receivepraise.entity.QReceivePraise.receivePraise;
import static com.dodok.honeypot.domain.sendpraise.entity.QSendPraise.sendPraise;

@RequiredArgsConstructor
public class GroupInfosQueryRepositoryImpl implements GroupInfosQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupWithMembersInfo> findGroupInfosByMemberId(Long memberId) {
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
                .where(eqMemberId(memberId))
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
                .where(eqMemberId(memberId))
                .orderBy(group.orderIdx.asc())
                .fetch()
        );

        List<GroupPraiseCountInfo> groupCountInfo = queryFactory
                .select(Projections.constructor(GroupPraiseCountInfo.class,
                        group.id,
                        receivePraise.countDistinct(),
                        sendPraise.countDistinct()
                ))
                .from(group)
                .leftJoin(group.sendPraises, sendPraise)
                .leftJoin(group.receivePraises, receivePraise)
                .where(eqMemberId(memberId))
                .groupBy(group.id)
                .fetch();

        Map<Long, GroupPraiseCountInfo> groupCountInfoMap = groupCountInfo.stream()
                .collect(Collectors.toMap(
                        GroupPraiseCountInfo::groupId,
                        info -> info
                ));

        return groupInfos.stream()
                .collect(Collectors.groupingBy(
                        GroupInfo::groupId,     // 그룹 id를 통해 Grouping
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                groupInfo -> {
                                    // GroupPraiseCountInfo를 가져오기
                                    GroupPraiseCountInfo countInfo = groupCountInfoMap.get(groupInfo.get(0).groupId());

                                    return GroupWithMembersInfo.of(   // 그룹 id를 통해 묶은 값들 중에 Id, Name, OrderIdx는 공통이니 하나만 뽑고 memberName은 distinct로 중복 허용하지 않고 가져오기
                                            groupInfo.get(0).groupId(),
                                            groupInfo.get(0).groupName(),
                                            groupInfo.get(0).orderIdx(),
                                            countInfo.receiveCount(),
                                            countInfo.sendCount(),
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
                .values().stream()
                .sorted(Comparator.comparing(GroupWithMembersInfo::orderIdx))
                .collect(Collectors.toList());
    }

    private BooleanExpression eqMemberId(Long memberId) {
        return group.member.id.eq(memberId);
    }
}
