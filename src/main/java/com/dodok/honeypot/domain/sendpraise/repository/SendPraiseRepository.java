package com.dodok.honeypot.domain.sendpraise.repository;

import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendPraiseRepository extends JpaRepository<SendPraise, Long> {
    Long countBySenderId(Long senderId);

}
