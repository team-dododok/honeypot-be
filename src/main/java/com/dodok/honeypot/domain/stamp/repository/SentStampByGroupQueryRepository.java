package com.dodok.honeypot.domain.stamp.repository;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;

import java.util.List;

public interface SentStampByGroupQueryRepository {
    List<StampDto> findAllSentStampByGroup(Long groupId);
}
