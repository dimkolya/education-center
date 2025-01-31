package com.dimkolya.educationcenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final Environment environment;

    @Async
    public void sendVerificationEmail(String to, String token) {
        String confirmationUrl = environment.getProperty("app.base-url")
                + "/verify-email?token=" + token;

        String text = String.format(
                "Please click the link below to verify your email:\n%s",
                confirmationUrl
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Email verification code");
        message.setText(text);
        mailSender.send(message);
    }
}
