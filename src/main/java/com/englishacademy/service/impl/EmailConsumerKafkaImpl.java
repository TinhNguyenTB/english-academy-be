package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.entity.FailedEmail;
import com.englishacademy.mapper.EmailMapper;
import com.englishacademy.mdc.TraceIdFilter;
import com.englishacademy.repository.FailedEmailRepository;
import com.englishacademy.service.EmailConsumerKafka;
import com.englishacademy.service.EmailProducerKafka;
import com.englishacademy.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailConsumerKafkaImpl implements EmailConsumerKafka {

    private final EmailService emailService;
    private final EmailProducerKafka emailProducerKafka;
    private static final int retryMaxNumber = 5;
    private final EmailMapper emailMapper;
    private final FailedEmailRepository failedEmailRepository;

    @KafkaListener(topics = "email-retry-topic", groupId = "email-group")
    @Override
    public void consumeEmail(EmailMessageDTO emailMessageDTO, @Header(name = TraceIdFilter.TRACE_ID, required = false) String traceIdBytes) {
           String traceId = traceIdBytes != null ? new String(traceIdBytes) : UUID.randomUUID().toString();
           try(MDC.MDCCloseable closeable = MDC.putCloseable(TraceIdFilter.TRACE_ID, traceId)) {
               try{
                   emailService.sendEmail(emailMessageDTO.getTo(), emailMessageDTO.getSubject(), emailMessageDTO.getBody());
               }catch (Exception e){
                   int currentRetryNumber = emailMessageDTO.getRetryNumber();
                   if (currentRetryNumber < retryMaxNumber) {
                       System.out.println("========== log RETRY number " + currentRetryNumber + " ==========");
                       emailMessageDTO.setRetryNumber(currentRetryNumber + 1);
                       emailProducerKafka.sendEmailToKafka(emailMessageDTO);
                   }else{
                       emailProducerKafka.sendEmailToDLT(emailMessageDTO);
                   }
               }
           }
    }

    @KafkaListener(topics = "email-retry-dlt-topic", groupId = "email-group")
    @Override
    public void consumeEmailDLT(EmailMessageDTO emailMessageDTO,  @Header(name = TraceIdFilter.TRACE_ID, required = false) String traceIdBytes) {
        String traceId = traceIdBytes != null ? new String(traceIdBytes) : UUID.randomUUID().toString();
        try (MDC.MDCCloseable closeable = MDC.putCloseable(TraceIdFilter.TRACE_ID, traceId)) {

            FailedEmail failedEmail = emailMapper.toEntity(emailMessageDTO);
            failedEmailRepository.save(failedEmail);

        }
    }
}
