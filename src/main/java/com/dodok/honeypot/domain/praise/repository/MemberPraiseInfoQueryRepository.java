package com.dodok.honeypot.domain.praise.repository;

import com.dodok.honeypot.domain.praise.dto.info.MemberPraiseInfo;

public interface MemberPraiseInfoQueryRepository {
    MemberPraiseInfo findMemberPraiseInfosByMemberId(Long memberId);
}
