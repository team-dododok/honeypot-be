package com.dodok.honeypot.domain.stamp.service;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import com.dodok.honeypot.domain.stamp.helper.SendStampByGroupHelper;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import com.dodok.honeypot.domain.stamp.mapper.SendStampByGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SendStampByGroupHelperService {
    private final SendStampByGroupHelper sendStampByGroupHelper;
    private final StampHelper stampHelper;

    private final SendStampByGroupMapper sendStampByGroupMapper;

    public StampInfoByGroupResDto execute(Long groupId) {
        List<StampDto> allStamp = stampHelper.getAllStamp();
        Map<String, Integer> sendStampInfoMap = new LinkedHashMap<>();
        for (StampDto stamp : allStamp) {
            sendStampInfoMap.put(stamp.stampName(), 0);
        }

        List<StampDto> sendStampByGroup = sendStampByGroupHelper.getSendStampByGroup(groupId);
        for(StampDto stamp : sendStampByGroup) {
            Integer currentValue = sendStampInfoMap.get(stamp.stampName());
            sendStampInfoMap.put(stamp.stampName(), currentValue + 1);
        }


        return sendStampByGroupMapper.toStampInfoByGroupResDto(sendStampInfoMap,allStamp);
    }
}
