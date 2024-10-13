package com.dodok.honeypot.domain.stamp.repository;

import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoneyStampRepository extends JpaRepository<HoneyStamp, Long> {

}
