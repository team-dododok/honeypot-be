package com.dodok.honeypot.global.redis.repository;

import com.dodok.honeypot.global.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
