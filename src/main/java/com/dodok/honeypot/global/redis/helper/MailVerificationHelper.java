package com.dodok.honeypot.global.redis.helper;

import com.dodok.honeypot.domain.auth.error.AuthErrorCode;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import com.dodok.honeypot.global.redis.entity.MailVerification;
import com.dodok.honeypot.global.redis.repository.MailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailVerificationHelper {
    private final MailVerificationRepository mailVerificationRepository;

    public void createMailVerification(String receiverMail, String verificationNumber) {
        MailVerification mailVerification = MailVerification.createMailVerification(receiverMail, verificationNumber);
        mailVerificationRepository.save(mailVerification);
    }

    public MailVerification findByIdOrElseThrow(String receiverMail) {
        return mailVerificationRepository.findById(receiverMail).orElseThrow(
                () -> new EntityNotFoundException(AuthErrorCode.RECEIVER_MAIL_NOT_FOUND)
        );
    }

}
