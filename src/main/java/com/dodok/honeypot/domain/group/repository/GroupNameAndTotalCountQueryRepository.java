package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupNamePraiseCountInfo;
import com.dodok.honeypot.domain.member.entity.Member;

import java.util.Optional;

public interface GroupNameAndTotalCountQueryRepository {
    Optional<GroupNamePraiseCountInfo> findNameAndPraiseCount(Long memberId, Member member);
}
