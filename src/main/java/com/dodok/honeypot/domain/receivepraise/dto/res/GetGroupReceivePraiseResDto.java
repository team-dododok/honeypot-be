package com.dodok.honeypot.domain.receivepraise.dto.res;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.global.dto.SliceInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetGroupReceivePraiseResDto(
        List<ReceivePraiseInfo> receivePraiseInfos,
        SliceInfo sliceInfo
) {
    public static GetGroupReceivePraiseResDto of(List<ReceivePraiseInfo> receivePraiseInfos, SliceInfo sliceInfo) {
        return GetGroupReceivePraiseResDto.builder()
                .receivePraiseInfos(receivePraiseInfos)
                .sliceInfo(sliceInfo)
                .build();
    }
}
