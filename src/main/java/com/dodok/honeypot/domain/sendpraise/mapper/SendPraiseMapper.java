package com.dodok.honeypot.domain.sendpraise.mapper;

import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetSendPraiseSentResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseInfoResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.global.dto.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SendPraiseMapper {
    public SendPraiseResDto toSendPraiseResDto(SendPraise sendPraise) {
        return SendPraiseResDto.of(sendPraise.getUuid());
    }

    public GetGroupSendPraiseResDto toGetGroupReceivePraiseResDto(List<SendPraiseInfo> sendPraiseInfos, List<ProfileImage> profileImageUrlList, PageInfo pageInfo) {
        List<SendPraiseInfoResDto> sendPraiseInfoResDtoList = sendPraiseInfos.stream()
                .map(sendPraiseInfo -> {
                    SendPraiseInfoResDto sendPraiseInfoResDto = SendPraiseInfoResDto.of(sendPraiseInfo, profileImageUrlList.get(getImageIdx(profileImageUrlList, sendPraiseInfo)).getImageUrl());
                    return sendPraiseInfoResDto;
                })
                .collect(Collectors.toList());
        return GetGroupSendPraiseResDto.of(sendPraiseInfoResDtoList, pageInfo);
    }

    private int getImageIdx(List<ProfileImage> profileImageUrlList, SendPraiseInfo sendPraiseInfo) {
        return Long.valueOf(sendPraiseInfo.sendPraiseId() % profileImageUrlList.size()).intValue();
    }

    public GetSendPraiseSentResDto toGetSendPraiseSentResDto(SendPraise sendPraise) {
        return GetSendPraiseSentResDto.of(sendPraise.getSendStatus());
    }
}

