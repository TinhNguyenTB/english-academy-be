package com.englishacademy.service;

import com.englishacademy.dto.request.EmailMessageDTO;

public interface EmailProducerKafka {
    void sendEmailToKafka(EmailMessageDTO emailMessageDTO);

    void sendEmailToDLT(EmailMessageDTO emailMessageDTO);
}
