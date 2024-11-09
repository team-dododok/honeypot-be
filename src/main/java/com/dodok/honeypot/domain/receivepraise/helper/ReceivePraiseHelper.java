package com.dodok.honeypot.domain.receivepraise.helper;

import com.dodok.honeypot.domain.receivepraise.repository.ReceivePraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;

    // TODO : 꿀(칭찬)을 받는 로직 구현 시, CheckReceivePraiseBadgeHelper.updateReceivePraiseBadge() 호출할 것.
}
