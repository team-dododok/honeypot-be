package com.dodok.honeypot.domain.praise.helper;

import com.dodok.honeypot.domain.praise.repository.ReceivePraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;

    // TODO : 꿀(칭찬)을 받는 로직 구현 시, CheckBadgeAchievementHelper.updateReceivePraiseBadge() 호출할 것.
}
