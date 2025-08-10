package com.englishacademy.service.impl;

import com.englishacademy.entity.EmailEntity;
import com.englishacademy.enums.EmailStatusEnum;
import com.englishacademy.repository.EmailRepository;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Value("${SENDGRID_API_KEY}")
    private String sendGridKey;

    @Async
    @Override
    public void sendEmail(String to, String subject, String content) {

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setFrom("phamxuanhoanglong@gmail.com");
        emailEntity.setTo(to);
        emailEntity.setSubject(subject);
        emailEntity.setContent(content);
        emailEntity.setStatus(EmailStatusEnum.PENDING);
        emailEntity.setRetryNum(0);
        emailEntity.setCreateAt(LocalDateTime.now());

        emailRepository.save(emailEntity);
    }

    @Override
    public void sendEmailGrid(EmailEntity emailEntity) {
        SendGrid sendGrid = new SendGrid(sendGridKey);

        Email from = new Email(emailEntity.getFrom());
        Email toEmail = new Email(emailEntity.getTo());
        String subjectEmail = emailEntity.getSubject();
        Content contentEmail = new Content("text/html", emailEntity.getContent());
        Mail mail = new Mail(from, subjectEmail, toEmail, contentEmail);
        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);
            log.info("HTTP STATUS CODE: " + response.getStatusCode());
            log.info(request.getBody());
            if (response.getStatusCode() != 202) {
                emailEntity.setRetryNum(emailEntity.getRetryNum() + 1);
                if (emailEntity.getRetryNum() >= 5) {
                    emailEntity.setStatus(EmailStatusEnum.FAILED_PERMANENT);
                } else {
                    emailEntity.setStatus(EmailStatusEnum.FAILED);
                }
                log.error("Failed to send email to {}. Status: {}, Response: {}", emailEntity.getTo(), response.getStatusCode(), response.getBody());
            }else{
                emailEntity.setStatus(EmailStatusEnum.SUCCESS);
            }
        } catch (IOException e) {
            log.error("Failed to send email to {}", emailEntity.getTo(), e.getMessage());
            emailEntity.setStatus(EmailStatusEnum.FAILED);
            emailEntity.setRetryNum(emailEntity.getRetryNum() + 1);
            if(emailEntity.getRetryNum() >= 5){
                emailEntity.setStatus(EmailStatusEnum.FAILED_PERMANENT);
            }
        }
        emailEntity.setLastTryAt(LocalDateTime.now());
        emailRepository.save(emailEntity);
    }
}
