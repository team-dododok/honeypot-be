package com.dodok.honeypot.domain.sendpraise.repository;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SendPraiseRepository extends JpaRepository<SendPraise, Long>,
        GroupSendPraiseGetInfoQueryRepository {
    Long countBySenderId(Long senderId);

    Optional<SendPraise> findByUuid(String uuid);

    Optional<SendPraise> findBySenderAndIdOrElseThrow(Member member, Long sendPraiseId);
}
