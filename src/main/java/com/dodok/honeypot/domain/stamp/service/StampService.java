package com.dodok.honeypot.domain.stamp.service;

import com.dodok.honeypot.domain.stamp.dto.res.AllStampResDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StampService {
    private final StampHelper stampHelper;

    /**
     * 존재하는 모든 꿀도장 조회 로직
     * @return 모든 꿀도장 종류 반환
     */
    public AllStampResDto getAllStamp(){
        List<StampDto> allStamp = stampHelper.getAllStamp();
        return AllStampResDto.of(allStamp);
    }

}
