package com.dodok.honeypot.domain.stamp.mapper;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class SentStampByGroupMapper {

    public StampInfoByGroupResDto toStampInfoByGroupResDto(Map<String, Integer> sentStampInfoMap, List<StampDto> allStamp) {
        int totalSum = sentStampInfoMap.values().stream().mapToInt(Integer::intValue).sum();

        List<StampInfoByGroupDto> stampInfoByGroupDtos = new ArrayList<>();
        for (StampDto stamp : allStamp) {
            stampInfoByGroupDtos.add(StampInfoByGroupDto
                    .of(stamp.imageUrl(), stamp.stampName(), sentStampInfoMap.get(stamp.stampName()), totalSum)
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
