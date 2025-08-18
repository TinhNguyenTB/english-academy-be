package com.englishacademy.service;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
    void handleWebhookEvents(List<Map<String, Object>> events);
}
