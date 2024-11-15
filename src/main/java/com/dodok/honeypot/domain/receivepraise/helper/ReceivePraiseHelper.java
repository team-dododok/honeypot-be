package com.dodok.honeypot.domain.receivepraise.helper;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.error.ReceivedPraiseErrorCode;
import com.dodok.honeypot.domain.receivepraise.repository.ReceivePraiseRepository;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;

    public ReceivePraise findByIdOrElseThrow(Long receivedPraiseId) {
        return receivePraiseRepository.findById(receivedPraiseId).orElseThrow(
                () -> new EntityNotFoundException(ReceivedPraiseErrorCode.RECEIVED_PRAISE_ENTITY_NOT_FOUND)
        );
    }

    public void saveReceivePraise(Member receiver, SendPraise sendPraise, Group group) {
        receivePraiseRepository.save(ReceivePraise.createReceivePraise(receiver, sendPraise, group));
    }

    public void deleteReceivedPraiseService(ReceivePraise receivePraise) {
        receivePraiseRepository.delete(receivePraise);
    }

}
