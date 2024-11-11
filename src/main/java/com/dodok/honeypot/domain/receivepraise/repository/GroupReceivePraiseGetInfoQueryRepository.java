package com.dodok.honeypot.domain.receivepraise.repository;

import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GroupReceivePraiseGetInfoQueryRepository {
    Slice<ReceivePraiseInfo> findReceivePraiseInfosByGroupId(Long groupId, Pageable pageable);
}
