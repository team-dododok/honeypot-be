package com.dodok.honeypot.domain.group.dto.res;

import com.dodok.honeypot.domain.group.dto.GroupMembersInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetMyGroupsResDto(
        List<GroupMembersInfo> groupMembersInfos
) {
    public static GetMyGroupsResDto of(List<GroupMembersInfo> groupMembersInfos) {
        return GetMyGroupsResDto.builder()
                .groupMembersInfos(groupMembersInfos)
                .build();

    }
}
