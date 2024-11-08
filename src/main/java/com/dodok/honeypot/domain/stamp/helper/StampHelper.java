package com.dodok.honeypot.domain.stamp.helper;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StampHelper {

    private final StampRepository honeyStampRepository;

    /**
     * 모든 꿀도장 정보를 조회하는 로직
     * @return 모든 꿀도장 정보
     */
    public List<StampDto> getAllStamp(){
        List<StampDto> stampDtos = honeyStampRepository.findAllBy();
        return stampDtos;
    }
}
