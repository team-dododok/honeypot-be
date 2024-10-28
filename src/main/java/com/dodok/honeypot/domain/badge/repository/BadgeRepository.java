package com.dodok.honeypot.domain.badge.repository;

import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
import com.dodok.honeypot.domain.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<AllBadgeInfo> findAllBy();

    // TODO : 뱃지의 description 컬럼에 unique 또는 인덱스 설정
    Optional<Badge> findByDescription(String description);
}
