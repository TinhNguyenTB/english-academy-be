package com.englishacademy.service;

import com.englishacademy.entity.EmailEntity;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
    void sendEmailGrid(EmailEntity emailEntity);
}
