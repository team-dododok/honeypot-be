package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberGetInfoQueryRepository {
    Page<MemberInfo> findAllMembersByName(String name, Pageable pageable);
}
