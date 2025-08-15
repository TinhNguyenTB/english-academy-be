package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.service.EmailProducerKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailProducerKafkaImpl implements EmailProducerKafka {

    private final KafkaTemplate<String, EmailMessageDTO> kafkaTemplate;
    private static final String TOPIC = "email-retry-topic";
    private static final String TOPIC_DLT = "email-retry-dlt-topic";

    @Override
    public void sendEmailToKafka(EmailMessageDTO emailMessageDTO) {
        emailMessageDTO.setCreateAt(LocalDateTime.now());
        kafkaTemplate.send(TOPIC, emailMessageDTO);
    }

    @Override
    public void sendEmailToDLT(EmailMessageDTO emailMessageDTO) {
        kafkaTemplate.send(TOPIC_DLT, emailMessageDTO);
    }
}
