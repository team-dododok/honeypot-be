package com.dodok.honeypot.domain.stamp.service;

import com.dodok.honeypot.domain.stamp.dto.res.AllStampResDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StampService {
    private final StampHelper stampHelper;

    /**
     * 존재하는 모든 꿀도장 조회 로직
     * @return 모든 꿀도장 종류 반환
     */
    public AllStampResDto execute(){
        List<StampDto> allStamp = stampHelper.getAllStamp();
        return AllStampResDto.of(allStamp);
    }

}
