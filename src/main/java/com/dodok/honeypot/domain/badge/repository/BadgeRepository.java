package com.dodok.honeypot.domain.badge.repository;

import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
import com.dodok.honeypot.domain.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<AllBadgeInfo> findAllBy();
}
