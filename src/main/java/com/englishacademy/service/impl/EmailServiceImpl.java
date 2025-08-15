package com.englishacademy.service.impl;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.exception.BadRequestException;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailProducerKafka emailProducer;

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
                throw new BadRequestException("SendGrid Status: " + response.getStatusCode());
            }
        } catch (IOException e) {
                throw new BadRequestException("SendGrid Error: " + e.getMessage());
        }
    }
}