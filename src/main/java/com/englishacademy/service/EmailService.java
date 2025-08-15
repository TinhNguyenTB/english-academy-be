package com.englishacademy.service;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
}
