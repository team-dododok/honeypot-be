package com.dodok.honeypot.domain.receivepraise.service;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.receivepraise.dto.res.DetailReceivedPraiseResDto;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetDetailReceivedPraiseService {

    private final ReceivePraiseHelper receivePraiseHelper;
    private final MemberHelper memberHelper;

    public DetailReceivedPraiseResDto execute(Long memberId, Long receivedPraiseId){
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        ReceivePraise receivePraise = receivePraiseHelper.findByReceiverAndIdOrElseThrow(member,receivedPraiseId);
        return DetailReceivedPraiseResDto.of(receivePraise);
    }

}
