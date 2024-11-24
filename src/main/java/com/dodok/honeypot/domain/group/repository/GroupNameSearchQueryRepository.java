package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;

import java.util.List;

public interface GroupNameSearchQueryRepository {
    List<SearchGroupInfo> findAllByNameContainsAndMember(Long memberId, String groupName);
}
