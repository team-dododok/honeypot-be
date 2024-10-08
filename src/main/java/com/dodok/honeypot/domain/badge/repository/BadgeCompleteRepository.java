package com.dodok.honeypot.domain.badge.repository;

import com.dodok.honeypot.domain.badge.entity.BadgeComplete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeCompleteRepository extends JpaRepository<BadgeComplete, Long> {

}
