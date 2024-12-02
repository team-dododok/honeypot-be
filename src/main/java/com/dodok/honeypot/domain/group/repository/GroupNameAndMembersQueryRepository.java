package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.GroupMembersInfo;

import java.util.List;

public interface GroupNameAndMembersQueryRepository {
    List<GroupMembersInfo> findGroupNameAndMembersAndDeletedAtIsNull(Long memberId, List<Long> groupIds);
}
