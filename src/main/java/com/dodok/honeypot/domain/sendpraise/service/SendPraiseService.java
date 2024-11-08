package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.badge.helper.CheckSendPraiseBadgeHelper;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.sendpraise.dto.req.SendPraiseReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import com.dodok.honeypot.domain.sendpraise.mapper.SendPraiseMapper;
import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SendPraiseService {
    private final SendPraiseHelper sendPraiseHelper;
    private final MemberHelper memberHelper;
    private final GroupHelper groupHelper;
    private final StampHelper stampHelper;
    private final CheckSendPraiseBadgeHelper checkSendPraiseBadgeHelper;

    private final SendPraiseMapper sendPraiseMapper;

    /**
     * 전송 할 보낸칭찬을 생성하는 로직
     * @param req 보낸칭찬을 만들기 위한 정보들
     * @return 만들어진 보낸칭찬의 uuid
     */
    @Transactional()
    public SendPraiseResDto createSendPraise(SendPraiseReqDto req) {
        Member sender = memberHelper.findMemberByIdOrElseThrow(req.senderId());
        Group group = groupHelper.findGroupByIdOrElseThrow(req.groupId());
        HoneyStamp stamp = stampHelper.findByStampIdOrElseThrow(req.honeyStampId());

        SendPraise sendPraise = sendPraiseHelper.createSendPraise(req.title(), req.content(), req.projectStatus(), req.receiverName(),
                sender, group, stamp);

        // TODO : @Async를 이용하여 비동기로 변경
        checkSendPraiseBadgeHelper.updateSendPraiseBadge(req.senderId());

        return sendPraiseMapper.toSendPraiseReqDto(sendPraise);


    }


}
