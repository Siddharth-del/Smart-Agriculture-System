package com.SmartAgriculture.Cropp.Service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
    void sendWelcomeEmail(String toEmail, String username);
    void sendAlertEmail(String toEmail, String title, String message);
}