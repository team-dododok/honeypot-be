package com.dodok.honeypot.domain.auth.service;

import com.dodok.honeypot.domain.auth.error.AuthErrorCode;
import com.dodok.honeypot.global.error.exception.InternalServerException;
import com.dodok.honeypot.global.redis.helper.MailVerificationHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private static final String senderEmail = "team.dododok@gmail.com";

    private final MailVerificationHelper mailVerificationHelper;
    private final JavaMailSender javaMailSender;

    public void execute(String receiverMail) {
        String verificationNumber = createVerificationNumber();

        try {
            MimeMessage message = CreateMail(receiverMail, verificationNumber);
            javaMailSender.send(message);
            mailVerificationHelper.createMailVerification(receiverMail,verificationNumber);

        } catch (Exception e) {
            throw new InternalServerException(AuthErrorCode.MAIL_VERIFICATION_ERROR);
        }

    }

    private MimeMessage CreateMail(String receiverMail, String verificationNumber) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, receiverMail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + verificationNumber + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new InternalServerException(AuthErrorCode.MAIL_VERIFICATION_ERROR);
        }

        return message;
    }


    private String createVerificationNumber() {
        return String.valueOf((int)(Math.random() * (999999 - 100000 + 1) + 100000)); // 100000 ~ 999999
    }

}
