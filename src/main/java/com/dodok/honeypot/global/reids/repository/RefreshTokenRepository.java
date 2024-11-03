package com.dodok.honeypot.global.reids.repository;

import com.dodok.honeypot.global.reids.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
