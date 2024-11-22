package com.dodok.honeypot.domain.receivepraise.service;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteReceivedPraiseService {
    private final ReceivePraiseHelper receivePraiseHelper;
    private final MemberHelper memberHelper;

    /**
     * receivedPraiseId를 통해 받은칭찬 엔티티를 삭제하는 로직
     * @param receivedPraiseId 받은칭찬Id
     */
    public void execute(Long memberId, Long receivedPraiseId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        receivePraiseHelper.deleteReceivedPraiseByMemberId(member, receivedPraiseId);
    }
}
