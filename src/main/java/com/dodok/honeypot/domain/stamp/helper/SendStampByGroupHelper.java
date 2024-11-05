package com.dodok.honeypot.domain.stamp.helper;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SendStampByGroupHelper {
    private final StampRepository stampRepository;

    public List<StampDto> getSendStampByGroup(Long groupId) {
        return  stampRepository.findAllSendStampByGroup(groupId);
    }
}
