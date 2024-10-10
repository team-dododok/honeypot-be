package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberGetInfoQueryRepository {

}
