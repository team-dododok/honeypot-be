package com.dodok.honeypot.domain.group.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GroupWithMembersInfo(
        Long groupId,
        String groupName,
        Integer orderIdx,
        List<GroupMemberNameInfo> groupMembers
) {
    public static GroupWithMembersInfo of(Long groupId, String groupName, Integer orderIdx, List<GroupMemberNameInfo> groupMembers) {
        return GroupWithMembersInfo.builder()
                .groupId(groupId)
                .groupName(groupName)
                .orderIdx(orderIdx)
                .groupMembers(groupMembers)
                .build();
    }
}
