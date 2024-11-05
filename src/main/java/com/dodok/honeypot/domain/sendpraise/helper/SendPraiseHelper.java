package com.dodok.honeypot.domain.sendpraise.helper;

import com.dodok.honeypot.domain.sendpraise.repository.SendPraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendPraiseHelper {

    private final SendPraiseRepository sendPraiseRepository;

    // TODO : 꿀(칭찬)을 보내는 로직 구현 시, CheckSendPraiseBadgeHelper.updateSendPraiseBadge() 호출할 것.

}
