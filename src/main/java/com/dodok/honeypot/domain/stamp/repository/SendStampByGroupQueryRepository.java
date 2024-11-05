package com.dodok.honeypot.domain.stamp.repository;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;

import java.util.List;

public interface SendStampByGroupQueryRepository {
    List<StampDto> findAllSendStampByGroup(Long groupId);
}
