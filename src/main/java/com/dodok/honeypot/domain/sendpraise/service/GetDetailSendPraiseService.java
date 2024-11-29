package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.sendpraise.dto.res.DetailSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class GetDetailSendPraiseService {

    private final MemberHelper memberHelper;
    private final SendPraiseHelper sendPraiseHelper;

    public DetailSendPraiseResDto execute(Long memberId, Long sendPraiseId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        SendPraise sendPraise = sendPraiseHelper.findBySenderAndIdOrElseThrow(member, sendPraiseId);
        return DetailSendPraiseResDto.of(sendPraise);
    }
}
