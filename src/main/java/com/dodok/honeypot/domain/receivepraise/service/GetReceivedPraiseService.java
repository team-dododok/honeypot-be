package com.dodok.honeypot.domain.receivepraise.service;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetReceivedPraiseResDto;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import com.dodok.honeypot.domain.receivepraise.mapper.ReceivePraiseMapper;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetReceivedPraiseService {
    private final List<SendStatus> sendStatuses = Arrays.asList(SendStatus.DIRECT, SendStatus.GROUP, SendStatus.MYSELF);

    private final SendPraiseHelper sendPraiseHelper;
    private final MemberHelper memberHelper;
    private final ReceivePraiseHelper receivePraiseHelper;

    public GetReceivedPraiseResDto execute(String praiseUuid, Optional<Long> memberId) {
        SendPraise sendPraise = sendPraiseHelper.findByUuidAndSendStatusesOrElseThrow(praiseUuid, sendStatuses);

        Long savedGroupId = -1L; // 내가 칭찬을 이미 받은 경우, 칭찬이 저장된 그룹의 id
        SendStatus sendStatus = sendPraise.getSendStatus();

        if (memberId.isPresent()) {
            // 내가 보낸 칭찬인 경우
            if (Objects.equals(sendPraise.getSender().getId(), memberId.get())) {
                sendStatus = SendStatus.MYSELF;
                return ReceivePraiseMapper.getReceivedPraiseResDto(sendPraise, sendStatus, savedGroupId);
            }

            Member receiver = memberHelper.findMemberByIdOrElseThrow(memberId.get());
            // 이미 내가 저장한 칭찬인지 확인
            List<ReceivePraise> allReceivedPraise = receivePraiseHelper.findAllReceivedPraise(sendPraise.getId());

            for (ReceivePraise receivePraise : allReceivedPraise) {
                if (Objects.equals(receivePraise.getReceiver().getId(), receiver.getId())) {
                    savedGroupId = receivePraise.getGroup().getId();
                    break;
                }
            }
        }

        return ReceivePraiseMapper.getReceivedPraiseResDto(sendPraise, sendStatus, savedGroupId);
    }
}
