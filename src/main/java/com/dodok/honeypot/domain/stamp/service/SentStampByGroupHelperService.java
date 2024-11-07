package com.dodok.honeypot.domain.stamp.service;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import com.dodok.honeypot.domain.stamp.helper.SentStampByGroupHelper;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import com.dodok.honeypot.domain.stamp.mapper.SentStampByGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SentStampByGroupHelperService {
    private final SentStampByGroupHelper sentStampByGroupHelper;
    private final StampHelper stampHelper;

    private final SentStampByGroupMapper sentStampByGroupMapper;

    public StampInfoByGroupResDto execute(Long groupId) {
        List<StampDto> allStamp = stampHelper.getAllStamp();
        Map<String, Integer> sentStampInfoMap = new LinkedHashMap<>();
        for (StampDto stamp : allStamp) {
            sentStampInfoMap.put(stamp.stampName(), 0);
        }

        List<StampDto> sentStampByGroup = sentStampByGroupHelper.getSentStampByGroup(groupId);
        for(StampDto stamp : sentStampByGroup) {
            Integer currentValue = sentStampInfoMap.get(stamp.stampName());
            sentStampInfoMap.put(stamp.stampName(), currentValue + 1);
        }


        return sentStampByGroupMapper.toStampInfoByGroupResDto(sentStampInfoMap,allStamp);
    }
}
