package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;

import java.util.List;

public interface GroupInfosQueryRepository {
    List<GroupWithMembersInfo> findGroupInfosByMemberIdAndDeletedAtIsNull(Long memberId);
}
