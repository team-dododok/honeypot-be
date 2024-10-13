package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

}
