package com.dodok.honeypot.domain.stamp.helper;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StampByGroupHelper {
    private final StampRepository stampRepository;

    /**
     * 그룹별로 보낸 꿀도장을 전체 조회하는 헬퍼
     * @param groupId 그룹id
     * @return 보낸 꿀도장의 전체 정보
     */
    public List<StampDto> getSentStampByGroup(Long groupId) {
        return stampRepository.findAllSentStampByGroup(groupId);
    }

    /**
     * 그룹별로 보낸 꿀도장을 전체 조회하는 헬퍼
     * @param groupId 그룹id
     * @return 보낸 꿀도장의 전체 정보
     */
    public List<StampDto> getReceivedStampByGroup(Long groupId) {
        return stampRepository.findAllReceivedStampByGroup(groupId);
    }

    /**
     * 멤버id를 기준으로 보낸 꿀도장을 전체 조회하는 헬퍼
     * @param memberId 그룹id
     * @return 보낸 꿀도장의 전체 정보
     */
    public List<StampDto> getReceivedStampByMember(Long memberId) {
        return stampRepository.findAllReceivedStampByMember(memberId);
    }
}
