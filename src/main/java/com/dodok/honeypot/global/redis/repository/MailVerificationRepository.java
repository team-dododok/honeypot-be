package com.dodok.honeypot.global.redis.repository;

import com.dodok.honeypot.global.redis.entity.MailVerification;
import org.springframework.data.repository.CrudRepository;

public interface MailVerificationRepository extends CrudRepository<MailVerification, String> {
}
