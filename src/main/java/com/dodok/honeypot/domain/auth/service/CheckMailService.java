package com.dodok.honeypot.domain.auth.service;

import com.dodok.honeypot.domain.auth.error.AuthErrorCode;
import com.dodok.honeypot.global.error.exception.BusinessException;
import com.dodok.honeypot.global.redis.entity.MailVerification;
import com.dodok.honeypot.global.redis.helper.MailVerificationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CheckMailService {
    private final MailVerificationHelper mailVerificationHelper;

    public void execute(String receiverMail,String verificationNumber) {
        MailVerification mailVerification = mailVerificationHelper.findByIdOrElseThrow(receiverMail);
        if (!Objects.equals(mailVerification.getVerificationNumber(), verificationNumber)) {
            throw new BusinessException(AuthErrorCode.VERIFICATION_NUMBER_INCORRECT);
        }
    }

}
