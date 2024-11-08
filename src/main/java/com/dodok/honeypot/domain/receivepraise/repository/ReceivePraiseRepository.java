package com.dodok.honeypot.domain.receivepraise.repository;

import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivePraiseRepository extends JpaRepository<ReceivePraise, Long> {
    Long countByReceiverId(Long receiverId);
}
