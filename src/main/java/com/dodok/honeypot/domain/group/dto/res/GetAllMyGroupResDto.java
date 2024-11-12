package com.dodok.honeypot.domain.group.dto.res;

import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAllMyGroupResDto(
        List<GroupWithMembersInfo> groupWithMembersInfos
) {
    public static GetAllMyGroupResDto of(List<GroupWithMembersInfo> groupWithMembersInfos) {
        return GetAllMyGroupResDto.builder()
                .groupWithMembersInfos(groupWithMembersInfos)
                .build();

    }
}
