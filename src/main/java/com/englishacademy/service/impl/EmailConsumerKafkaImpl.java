package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.service.EmailConsumerKafka;
import com.englishacademy.service.EmailProducerKafka;
import com.englishacademy.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConsumerKafkaImpl implements EmailConsumerKafka {

    private final EmailService emailService;
    private final EmailProducerKafka emailProducerKafka;
    private static final int retryMaxNumber = 5;

    @KafkaListener(topics = "email-retry-topic", groupId = "email-group")
    @Override
    public void consumeEmail(EmailMessageDTO emailMessageDTO) {
        try{
            emailService.sendEmail(emailMessageDTO.getTo(), emailMessageDTO.getSubject(), emailMessageDTO.getBody());
        }catch (Exception e){
            int currentRetryNumber = emailMessageDTO.getRetryNumber();
            if (currentRetryNumber < retryMaxNumber) {
                emailMessageDTO.setRetryNumber(currentRetryNumber + 1);
                emailProducerKafka.sendEmailToKafka(emailMessageDTO);
            }else{
               emailProducerKafka.sendEmailToDLT(emailMessageDTO);
            }
        }
    }

    @KafkaListener(topics = "email-retry-dlt-topic", groupId = "email-group")
    @Override
    public void consumeEmailDLT(EmailMessageDTO emailMessageDTO) {

    }
}
