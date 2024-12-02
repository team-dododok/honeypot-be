package com.dodok.honeypot.domain.group.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GroupMembersPraiseCountInfo(
        Long groupId,
        String groupName,
        Integer orderIdx,
        Long receiveCount,
        Long sendCount,
        List<GroupMemberNameInfo> groupMembers
) {
    public static GroupMembersPraiseCountInfo of(Long groupId, String groupName, Integer orderIdx, Long receiveCount, Long sendCount, List<GroupMemberNameInfo> groupMembers) {
        return GroupMembersPraiseCountInfo.builder()
                .groupId(groupId)
                .groupName(groupName)
                .orderIdx(orderIdx)
                .receiveCount(receiveCount)
                .sendCount(sendCount)
                .groupMembers(groupMembers)
                .build();
    }
}
