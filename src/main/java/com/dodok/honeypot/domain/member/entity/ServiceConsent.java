package com.dodok.honeypot.domain.member.entity;

import com.dodok.honeypot.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_service_consent")
@Getter
public class ServiceConsent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_consent_id")
    private Long id;

    @Column(name = "service_term", nullable = false)
    private Boolean serviceTerm;

    @Column(name = "personal_info")
    private Boolean personalInfo;

    @Column(name = "email_marketing")
    private Boolean emailMarketing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
