package com.dodok.honeypot.domain.praise.repository;

import com.dodok.honeypot.domain.praise.entity.SendPraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendPraiseRepository extends JpaRepository<SendPraise, Long> {

}
