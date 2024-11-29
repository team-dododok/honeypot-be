package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceConsentRepository extends JpaRepository<ServiceConsent, Long> {
    Optional<ServiceConsent> findByMember(Member member);
}
