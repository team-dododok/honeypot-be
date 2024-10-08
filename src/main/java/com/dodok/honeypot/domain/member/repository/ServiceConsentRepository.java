package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceConsentRepository extends JpaRepository<ServiceConsent, Long> {

}
