package com.dodok.honeypot.domain.sendpraise.repository;

import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupSendPraiseGetInfoQueryRepository {
    Page<SendPraiseInfo> findSendPraiseInfosByGroupIdAndSendStatusIsTrue(Long groupId, Pageable pageable);
}
