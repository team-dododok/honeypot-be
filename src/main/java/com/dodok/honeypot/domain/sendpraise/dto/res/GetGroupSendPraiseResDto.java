package com.dodok.honeypot.domain.sendpraise.dto.res;

import com.dodok.honeypot.global.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetGroupSendPraiseResDto(
        List<SendPraiseInfoResDto> sendPraiseInfos,
        PageInfo pageInfo
) {
    public static GetGroupSendPraiseResDto of(List<SendPraiseInfoResDto> sendPraiseInfos, PageInfo pageInfo) {
        return GetGroupSendPraiseResDto.builder()
                .sendPraiseInfos(sendPraiseInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
