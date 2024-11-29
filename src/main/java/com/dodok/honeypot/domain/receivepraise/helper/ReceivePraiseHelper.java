package com.dodok.honeypot.domain.receivepraise.helper;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.error.ReceivedPraiseErrorCode;
import com.dodok.honeypot.domain.receivepraise.repository.ReceivePraiseRepository;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;

    // TODO : 꿀(칭찬)을 받는 로직 구현 시, CheckReceivePraiseBadgeHelper.updateReceivePraiseBadge() 호출할 것.

    public Page<ReceivePraiseInfo> getGroupReceivePraiseInfos(Long groupId, Pageable pageable) {
        return receivePraiseRepository.findReceivePraiseInfosByGroupId(groupId, pageable);
    }

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

    /**
     * 보낸 칭찬의 id로 전체 받은 칭찬을 조회하는 로직
     * @param sendPraiseId 보낸칭찬의 id
     * @return
     */
    public List<ReceivePraise> findAllReceivedPraise(Long sendPraiseId) {
        return receivePraiseRepository.findAllBySendPraiseId(sendPraiseId);
    }

    public void deleteReceivedPraiseByMemberId(Member member, Long receivedPraiseId) {
        receivePraiseRepository.deleteByReceiverAndId(member, receivedPraiseId);
    }

}
