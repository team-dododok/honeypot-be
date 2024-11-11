package com.dodok.honeypot.domain.receivepraise.mapper;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetGroupReceivePraiseResDto;
import com.dodok.honeypot.global.dto.SliceInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceivePraiseMapper {

    public GetGroupReceivePraiseResDto toGetGroupReceivePraiseResDto(List<ReceivePraiseInfo> receivePraiseInfos, SliceInfo sliceInfo) {
        return GetGroupReceivePraiseResDto.of(receivePraiseInfos, sliceInfo);
    }
}
