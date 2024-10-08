package com.dodok.honeypot.domain.stamp.entity;

import com.dodok.honeypot.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "tb_honey_stamp")
@Entity
public class HoneyStamp extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "honey_stamp_id")
    private Long id;

    @Column(name = "stamp_name", nullable = false)
    private String stampName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
