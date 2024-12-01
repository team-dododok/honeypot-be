package com.dodok.honeypot.domain.receivepraise.mapper;

import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetGroupReceivePraiseResDto;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetReceivedPraiseResDto;
import com.dodok.honeypot.domain.receivepraise.dto.res.ReceivePraiseInfoResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.global.dto.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceivePraiseMapper {

    public static GetReceivedPraiseResDto getReceivedPraiseResDto(SendPraise sendPraise, Long savedGroupId) {
        return GetReceivedPraiseResDto.of(sendPraise.getContent(), sendPraise.getSender().getName(), sendPraise.getReceiverName(),
                sendPraise.getGroup().getName(), savedGroupId, sendPraise.getHoneyStamp().getImageUrl());
    }

    public GetGroupReceivePraiseResDto toGetGroupReceivePraiseResDto(List<ReceivePraiseInfo> receivePraiseInfos, List<ProfileImage> profileImageUrlList, PageInfo pageInfo) {
        List<ReceivePraiseInfoResDto> receivePraiseInfoResDtoList = receivePraiseInfos.stream()
                .map(sendPraiseInfo -> {
                    ReceivePraiseInfoResDto receivePraiseInfoResDto = ReceivePraiseInfoResDto.of(sendPraiseInfo, profileImageUrlList.get(getImageIdx(profileImageUrlList, sendPraiseInfo)).getImageUrl());
                    return receivePraiseInfoResDto;
                })
                .collect(Collectors.toList());
        return GetGroupReceivePraiseResDto.of(receivePraiseInfoResDtoList, pageInfo);
    }


    private int getImageIdx(List<ProfileImage> profileImageUrlList, ReceivePraiseInfo receivePraiseInfo) {
        return Long.valueOf(receivePraiseInfo.receivePraiseId() % profileImageUrlList.size()).intValue();
    }
}
