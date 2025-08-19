package com.englishacademy.service;

import com.englishacademy.dto.request.EmailMessageDTO;

public interface EmailConsumerKafka {
    void consumeEmail(EmailMessageDTO emailMessageDTO, String traceId);

    void consumeEmailDLT(EmailMessageDTO emailMessageDTO, String traceId);
}
