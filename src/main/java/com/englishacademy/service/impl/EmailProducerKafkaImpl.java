package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.mdc.TraceIdFilter;
import com.englishacademy.service.EmailProducerKafka;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailProducerKafkaImpl implements EmailProducerKafka {

    private final KafkaTemplate<String, EmailMessageDTO> kafkaTemplate;
    private static final String TOPIC = "email-retry-topic";
    private static final String TOPIC_DLT = "email-retry-dlt-topic";

    @Override
    public void sendEmailToKafka(EmailMessageDTO emailMessageDTO) {
        String traceId = ThreadContext.get(TraceIdFilter.TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
            ThreadContext.put(TraceIdFilter.TRACE_ID, traceId);
        }
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
        String traceId = ThreadContext.get(TraceIdFilter.TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
            ThreadContext.put(TraceIdFilter.TRACE_ID, traceId);
        }
        kafkaTemplate.send(
                MessageBuilder.withPayload(emailMessageDTO)
                        .setHeader(KafkaHeaders.TOPIC, TOPIC_DLT)
                        .setHeader(TraceIdFilter.TRACE_ID, traceId)
                        .build()
        );
    }
}
