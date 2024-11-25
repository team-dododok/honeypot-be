package com.dodok.honeypot.global.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "mail_verification",timeToLive = 60)
@Builder
@AllArgsConstructor
@Getter
public class MailVerification {

    @Id
    private String receiverMail;

    private String verificationNumber;

    public static MailVerification createMailVerification(String receiverMail, String verificationNumber){
        return MailVerification.builder()
                .receiverMail(receiverMail)
                .verificationNumber(verificationNumber)
                .build();
    }
}
