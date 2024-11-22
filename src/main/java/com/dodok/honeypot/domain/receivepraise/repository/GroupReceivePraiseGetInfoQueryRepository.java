package com.dodok.honeypot.domain.receivepraise.repository;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupReceivePraiseGetInfoQueryRepository {
    Page<ReceivePraiseInfo> findReceivePraiseInfosByGroupId(Long groupId, Pageable pageable);
}
