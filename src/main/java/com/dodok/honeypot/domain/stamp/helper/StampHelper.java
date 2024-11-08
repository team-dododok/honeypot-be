package com.dodok.honeypot.domain.stamp.helper;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import com.dodok.honeypot.domain.stamp.error.StampErrorCode;
import com.dodok.honeypot.domain.stamp.repository.StampRepository;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StampHelper {


    private final StampRepository honeyStampRepository;

    /**
     * 모든 꿀도장 정보를 조회하는 로직
     *
     * @return 모든 꿀도장 정보
     */
    public List<StampDto> getAllStamp() {
        List<StampDto> stampDtos = honeyStampRepository.findAllBy();
        return stampDtos;
    }

    public HoneyStamp findByStampIdOrElseThrow(Long stampId) {
        return honeyStampRepository.findById(stampId)
                .orElseThrow(() -> new EntityNotFoundException(StampErrorCode.STAMP_ENTITY_NOT_FOUND));
    }
}
