package com.dodok.honeypot.domain.receivepraise.dto.res;

import com.dodok.honeypot.global.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetGroupReceivePraiseResDto(
        List<ReceivePraiseInfoResDto> receivePraiseInfos,
        PageInfo pageInfo
) {
    public static GetGroupReceivePraiseResDto of(List<ReceivePraiseInfoResDto> receivePraiseInfos, PageInfo pageInfo) {
        return GetGroupReceivePraiseResDto.builder()
                .receivePraiseInfos(receivePraiseInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
