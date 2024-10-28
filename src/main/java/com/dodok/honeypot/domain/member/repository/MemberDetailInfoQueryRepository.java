package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;

import java.util.Optional;

public interface MemberDetailInfoQueryRepository {
    Optional<MemberInfo> findMemberDetailInfoByMemberId(Long memberId);
}
