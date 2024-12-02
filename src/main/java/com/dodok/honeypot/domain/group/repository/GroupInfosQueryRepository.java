package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupMembersPraiseCountInfo;

import java.util.List;

public interface GroupInfosQueryRepository {
    List<GroupMembersPraiseCountInfo> findGroupInfosByMemberIdAndDeletedAtIsNull(Long memberId);
}
