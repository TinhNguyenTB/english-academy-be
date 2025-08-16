package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.entity.FailedEmail;
import com.englishacademy.exception.BadRequestException;
import com.englishacademy.mapper.EmailMapper;
import com.englishacademy.repository.FailedEmailRepository;
import com.englishacademy.service.EmailProducerKafka;
import com.englishacademy.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailProducerKafka emailProducer;
    private final FailedEmailRepository failedEmailRepository;
    private final EmailMapper emailMapper;

    @Value("${SENDGRID_API_KEY}")
    private String sendGridKey;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SendGrid sendGrid = new SendGrid(sendGridKey);

        Email from = new Email("phamxuanhoanglong@gmail.com");
        Email toEmail = new Email(to);
        String subjectEmail = subject;
        Content contentEmail = new Content("text/html", content);
        Mail mail = new Mail(from, subjectEmail, toEmail, contentEmail);
        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response.getStatusCode() != 202) {

                EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
                emailMessageDTO.setTo(to);
                emailMessageDTO.setSubject(subject);
                emailMessageDTO.setBody(content);
                emailMessageDTO.setRetryNumber(1);
                emailMessageDTO.setLastRetryTime(LocalDateTime.now());
                emailMessageDTO.setCreateAt(LocalDateTime.now());

                try{
                    emailProducer.sendEmailToKafka(emailMessageDTO);
                }catch (Exception e){
                    failedEmailRepository.save(emailMapper.toEntity(emailMessageDTO));
                }
            }
        } catch (IOException e) {
            EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
            emailMessageDTO.setTo(to);
            emailMessageDTO.setSubject(subject);
            emailMessageDTO.setBody(content);
            emailMessageDTO.setRetryNumber(1);
            emailMessageDTO.setLastRetryTime(LocalDateTime.now());
            emailMessageDTO.setCreateAt(LocalDateTime.now());

            try{
                emailProducer.sendEmailToKafka(emailMessageDTO);
            }catch (Exception eX){
                failedEmailRepository.save(emailMapper.toEntity(emailMessageDTO));
            }
        }
    }

    @Override
    public void handleWebhookEvents(List<Map<String, Object>> events) {
        for (Map<String, Object> event : events) {
            String emailTo = (String) event.get("email");
            String subject = (String) event.getOrDefault("subject", "");
            String body = (String) event.getOrDefault("body", "");
            String eventType = (String) event.get("event");

            if ("bounce".equals(eventType) || "drop".equals(eventType) || "spamreport".equals(eventType)) {
                EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
                emailMessageDTO.setTo(emailTo);
                emailMessageDTO.setSubject(subject);
                emailMessageDTO.setBody(body);
                emailMessageDTO.setRetryNumber(1);
                emailMessageDTO.setLastRetryTime(LocalDateTime.now());
                emailMessageDTO.setCreateAt(LocalDateTime.now());

                try{
                    emailProducer.sendEmailToKafka(emailMessageDTO);
                }catch (Exception e){
                    failedEmailRepository.save(emailMapper.toEntity(emailMessageDTO));
                }

            }
        }
    }
}