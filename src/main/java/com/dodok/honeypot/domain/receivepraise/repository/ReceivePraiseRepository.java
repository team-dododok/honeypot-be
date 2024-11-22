package com.dodok.honeypot.domain.receivepraise.repository;

import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivePraiseRepository extends JpaRepository<ReceivePraise, Long> {
    Long countByReceiverId(Long receiverId);

    /**
     * 보낸 칭찬의 id로 전체 받은 칭찬을 조회하는 로직
     * @param sendPraiseId 보낸칭찬의 id
     * @return 받은칭찬 리스트
     *
     */
    List<ReceivePraise> findAllBySendPraiseId(Long sendPraiseId);
}
