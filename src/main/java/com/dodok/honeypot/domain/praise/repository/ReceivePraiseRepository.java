package com.dodok.honeypot.domain.praise.repository;

import com.dodok.honeypot.domain.praise.entity.ReceivePraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivePraiseRepository extends JpaRepository<ReceivePraise, Long> {

}
