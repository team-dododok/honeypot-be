package com.dodok.honeypot.domain.auth.repository;

import com.dodok.honeypot.domain.auth.entity.KakaoSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoSocialRepository extends JpaRepository<KakaoSocial, Long> {

}
