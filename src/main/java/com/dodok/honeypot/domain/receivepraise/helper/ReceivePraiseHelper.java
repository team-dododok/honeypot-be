package com.dodok.honeypot.domain.receivepraise.helper;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.repository.ReceivePraiseRepository;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;

    public void saveReceivePraise(Member receiver, SendPraise sendPraise, Group group) {
        receivePraiseRepository.save(ReceivePraise.createReceivePraise(receiver, sendPraise, group));
    }

    // TODO : 꿀(칭찬)을 받는 로직 구현 시, CheckReceivePraiseBadgeHelper.updateReceivePraiseBadge() 호출할 것.
}
