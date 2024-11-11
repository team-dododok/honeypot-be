package com.dodok.honeypot.domain.receivepraise.helper;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.domain.receivepraise.repository.ReceivePraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;

    // TODO : 꿀(칭찬)을 받는 로직 구현 시, CheckReceivePraiseBadgeHelper.updateReceivePraiseBadge() 호출할 것.

    public Slice<ReceivePraiseInfo> getGroupReceivePraiseInfos(Long groupId, Pageable pageable) {
        return receivePraiseRepository.findReceivePraiseInfosByGroupId(groupId, pageable);
    }
}
