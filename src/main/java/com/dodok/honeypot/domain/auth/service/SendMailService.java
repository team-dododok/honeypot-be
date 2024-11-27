package com.dodok.honeypot.domain.auth.service;

import com.dodok.honeypot.domain.auth.dto.req.SendMailReqDto;
import com.dodok.honeypot.domain.auth.error.AuthErrorCode;
import com.dodok.honeypot.global.error.exception.InternalServerException;
import com.dodok.honeypot.global.redis.helper.MailVerificationHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private static final String senderMail = "team.dododok@gmail.com";

    private final MailVerificationHelper mailVerificationHelper;
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Async
    public void execute(SendMailReqDto req) {
        String verificationNumber = createVerificationNumber();

        try {
            MimeMessage message = createMail(req.receiverMail(), verificationNumber);
            javaMailSender.send(message);
            mailVerificationHelper.createMailVerification(req.receiverMail(),verificationNumber);

        } catch (Exception e) {
            throw new InternalServerException(AuthErrorCode.MAIL_VERIFICATION_ERROR);
        }
    }

    private MimeMessage createMail(String receiverMail, String verificationNumber) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(senderMail);
            helper.setTo(receiverMail);
            helper.setSubject("[꿀단지] 이메일을 인증해주세요.");
            helper.setText(setContext(verificationNumber),true);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new InternalServerException(AuthErrorCode.MAIL_VERIFICATION_ERROR);
        }

        return message;
    }


    private String createVerificationNumber() {
        return String.valueOf((int)(Math.random() * (99999 - 10000 + 1) + 10000)); // 10000 ~ 99999
    }

    private String setContext(String verificationNumber) {
        final Context context = new Context();
        context.setVariable("verificationNumber",verificationNumber);
        return templateEngine.process("send-mail",context);
    }

}
