package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
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

    @Value("${SENDGRID_FROM_EMAIL}")
    private String fromEmail;

    private boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }
    private void saveFailedEmail(String to, String subject, String content) {
        EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
        emailMessageDTO.setTo(to);
        emailMessageDTO.setSubject(subject);
        emailMessageDTO.setBody(content);
        emailMessageDTO.setRetryNumber(0);
        emailMessageDTO.setCreateAt(LocalDateTime.now());
        emailMessageDTO.setLastRetryTime(LocalDateTime.now());

        failedEmailRepository.save(emailMapper.toEntity(emailMessageDTO));
        log.info("Saved failed email to DB: {} (reason: {})", to);
    }
    @Override
    public void sendEmail(String to, String subject, String content) {
        if (!isValidEmail(to)) {
            log.warn("Invalid email format: {}", to);
            saveFailedEmail(to, subject, content);
            return;
        }

        SendGrid sendGrid = new SendGrid(sendGridKey);

        Email from = new Email(fromEmail);
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