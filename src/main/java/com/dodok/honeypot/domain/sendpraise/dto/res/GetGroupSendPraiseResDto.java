package com.dodok.honeypot.domain.sendpraise.dto.res;

import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import com.dodok.honeypot.global.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetGroupSendPraiseResDto(
        List<SendPraiseInfo> sendPraiseInfos,
        PageInfo pageInfo
) {
    public static GetGroupSendPraiseResDto of(List<SendPraiseInfo> sendPraiseInfos, PageInfo pageInfo) {
        return GetGroupSendPraiseResDto.builder()
                .sendPraiseInfos(sendPraiseInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
