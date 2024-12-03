package com.dodok.honeypot.domain.receivepraise.service;

import com.dodok.honeypot.domain.badge.helper.CheckReceivePraiseBadgeHelper;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.error.GroupErrorCode;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.receivepraise.dto.req.SaveReceivePraiseReqDto;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import com.dodok.honeypot.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class SaveReceivePraiseService {
    private final List<SendStatus> sendStatuses = Arrays.asList(SendStatus.DIRECT, SendStatus.GROUP, SendStatus.MYSELF);

    private final MemberHelper memberHelper;
    private final GroupHelper groupHelper;
    private final ReceivePraiseHelper receivePraiseHelper;
    private final SendPraiseHelper sendPraiseHelper;
    private final CheckReceivePraiseBadgeHelper checkReceivePraiseBadgeHelper;

    /**
     * 칭찬uuid를 통해 칭찬을 저장하는 로직
     * @param req 칭찬의 uuid와 받는사람의 memberId
     */
    public void execute(Long memberId, SaveReceivePraiseReqDto req) {
        Member receiver = memberHelper.findMemberByIdOrElseThrow(memberId);
        SendPraise sendPraise = sendPraiseHelper.findByUuidAndSendStatusesOrElseThrow(req.praiseUuid(), sendStatuses);
        Group group = groupHelper.findGroupByIdOrElseThrow(req.groupId());

        // 저장하려는 그룹이 사용자가 만든 그룹이 아닌 경우
        if (!Objects.equals(group.getMember().getId(), memberId)) {
            throw new BusinessException(GroupErrorCode.GROUP_ENTITY_NOT_FOUND);
        }

        receivePraiseHelper.saveReceivePraise(receiver, sendPraise, group);

        // TODO : 비동기로 전환
        checkReceivePraiseBadgeHelper.updateReceivePraiseBadge(memberId);
    }
}
