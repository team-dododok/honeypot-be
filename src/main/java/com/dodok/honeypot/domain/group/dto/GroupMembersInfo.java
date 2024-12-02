package com.dodok.honeypot.domain.group.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GroupMembersInfo(
        Long groupId,
        String groupName,
        Integer orderIdx,
        List<GroupMemberNameInfo> groupMembers
) {
    public static GroupMembersInfo of(Long groupId, String groupName, Integer orderIdx, List<GroupMemberNameInfo> groupMembers) {
        return GroupMembersInfo.builder()
                .groupId(groupId)
                .groupName(groupName)
                .orderIdx(orderIdx)
                .groupMembers(groupMembers)
                .build();
    }
}
