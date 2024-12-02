package com.dodok.honeypot.domain.auth.repository;

import com.dodok.honeypot.domain.auth.entity.KakaoSocial;
import com.dodok.honeypot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KakaoSocialRepository extends JpaRepository<KakaoSocial, Long> {


    Optional<KakaoSocial> findByKakaoAuth(Long kakaoId);
    Optional<KakaoSocial> findByMember(Member member);
}
