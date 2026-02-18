package com.SmartAgriculture.Cropp.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            log.info("Email sent to: {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }

    @Override
    public void sendWelcomeEmail(String toEmail, String username) {
        String subject = "Welcome to Smart Agriculture System";
        String body = """
                Dear %s,

                Welcome to Smart Agriculture System!

                You have successfully registered.
                You can now:
                - Get Crop Recommendations
                - Detect Plant Diseases
                - Get AI Advisory
                - Monitor Irrigation

                Regards,
                Smart Agriculture System
                """.formatted(username);

        sendEmail(toEmail, subject, body);
    }

    @Override
    public void sendAlertEmail(String toEmail, String title, String message) {
        String subject = "Alert: " + title;
        String body = """
                Dear Farmer,

                ALERT: %s

                %s

                Please take necessary action immediately.

                Regards,
                Smart Agriculture System
                """.formatted(title, message);

        sendEmail(toEmail, subject, body);
    }
}