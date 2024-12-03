package com.dodok.honeypot.domain.sendpraise.repository;

import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SendPraiseRepository extends JpaRepository<SendPraise, Long>,
        GroupSendPraiseGetInfoQueryRepository {
    Long countBySenderId(Long senderId);
    Optional<SendPraise> findByUuid(String uuid);
    Optional<SendPraise> findByUuidAndSendStatusIn(String uuid, List<SendStatus> sendStatuses);

    List<SendPraise> findAllByGroupId(Long groupId);
}
