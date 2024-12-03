package com.dodok.honeypot.domain.praise.helper;

import com.dodok.honeypot.domain.praise.dto.info.MemberPraiseInfo;
import com.dodok.honeypot.domain.receivepraise.repository.ReceivePraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PraiseHelper {
    private final ReceivePraiseRepository receivePraiseRepository;

    public MemberPraiseInfo getMemberPraiseInfo(Long memberId) {
        return receivePraiseRepository.findMemberPraiseInfosByMemberIdAndSendStatusIsTrue(memberId);
    }
}
