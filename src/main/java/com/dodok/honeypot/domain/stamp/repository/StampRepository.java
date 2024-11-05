package com.dodok.honeypot.domain.stamp.repository;

import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampRepository extends JpaRepository<HoneyStamp, Long>, SendStampByGroupQueryRepository {
    List<StampDto> findAllBy();
}
