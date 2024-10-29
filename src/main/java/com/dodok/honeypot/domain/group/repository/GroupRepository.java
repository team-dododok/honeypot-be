package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    int countByMember(Member member);
    boolean existsByMemberAndName(Member member, String name);
}
