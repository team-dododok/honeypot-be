package com.dodok.honeypot.domain.group.dto.res;

import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetSearchGroupNameResDto(
        List<SearchGroupInfo> groupInfos
) {
    public static GetSearchGroupNameResDto of(List<SearchGroupInfo> groupInfos) {
        return GetSearchGroupNameResDto.builder()
                .groupInfos(groupInfos)
                .build();
    }
}
