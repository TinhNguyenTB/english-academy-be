package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.mdc.TraceIdFilter;
import com.englishacademy.service.EmailProducerKafka;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
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
        String traceId = MDC.get(TraceIdFilter.TRACE_ID);
        emailMessageDTO.setCreateAt(LocalDateTime.now());
        kafkaTemplate.send(
                MessageBuilder.withPayload(emailMessageDTO)
                        .setHeader(KafkaHeaders.TOPIC, TOPIC)
                        .setHeader(TraceIdFilter.TRACE_ID, traceId)
                        .build()
        );
    }

    @Override
    public void sendEmailToDLT(EmailMessageDTO emailMessageDTO) {
        String traceId = MDC.get(TraceIdFilter.TRACE_ID);
        kafkaTemplate.send(
          MessageBuilder.withPayload(emailMessageDTO)
                  .setHeader(KafkaHeaders.TOPIC, TOPIC_DLT)
                  .setHeader(TraceIdFilter.TRACE_ID, traceId)
                  .build()
        );
    }
}
