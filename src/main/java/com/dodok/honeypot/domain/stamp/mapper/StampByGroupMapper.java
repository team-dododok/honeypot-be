package com.dodok.honeypot.domain.stamp.mapper;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StampByGroupMapper {

    /**
     * 전체 꿀도장의 종류와 그룹별 받은 꿀도장을 종합하여 반환 정보로 변환하는 로직
     * @param allStamp 전체 꿀도장 종류
     * @param receivedStampByGroup 그룹별로 받은 꿀도장의 종류들
     * @return
     */
    public StampInfoByGroupResDto toStampInfoByGroupResDto(
            List<StampDto> allStamp,
            List<StampDto> receivedStampByGroup,
            List<StampDto> receivedStampTotal) {

        // 반환할 꿀도장 정보를 Map으로 초기화
        Map<String, Integer> sentStampInfoMap = new LinkedHashMap<>();
        Map<String, Integer> sentTotalStampInfoMap = new LinkedHashMap<>();

        for (StampDto stamp : allStamp) {
            sentStampInfoMap.put(stamp.stampName(), 0);
            sentTotalStampInfoMap.put(stamp.stampName(), 0);
        }

        // 그룹이 받은 꿀도장을 Map에 넣기
        for(StampDto stamp : receivedStampByGroup) {
            Integer currentValue = sentStampInfoMap.get(stamp.stampName());
            sentStampInfoMap.put(stamp.stampName(), currentValue + 1);
        }

        // 전체 꿀도장 정보를 Map에 넣기
        for(StampDto stamp : receivedStampTotal) {
            Integer currentValue = sentTotalStampInfoMap.get(stamp.stampName());
            sentTotalStampInfoMap.put(stamp.stampName(), 0);
            sentTotalStampInfoMap.put(stamp.stampName(), currentValue + 1);
        }

        // allStamp에 있는 정보를 반환할 dto에 맞게 변경
        List<StampInfoByGroupDto> stampInfoByGroupDtos = new ArrayList<>();
        for (StampDto stamp : allStamp) {
            stampInfoByGroupDtos.add(StampInfoByGroupDto.of(
                    stamp.imageUrl(),
                    stamp.stampName(),
                    sentStampInfoMap.get(stamp.stampName()),
                    sentTotalStampInfoMap.get(stamp.stampName()))
            );
        }

        // count를 기준으로 내림차순 정렬
        stampInfoByGroupDtos.sort(new Comparator<StampInfoByGroupDto>() {
            @Override
            public int compare(StampInfoByGroupDto dto1, StampInfoByGroupDto dto2) {
                return dto2.count() - dto1.count(); // 내림차순 정렬
            }
        });

        return StampInfoByGroupResDto.of(stampInfoByGroupDtos);
    }
}
