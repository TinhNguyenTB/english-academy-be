package com.englishacademy.service.impl;

import com.englishacademy.exception.BadRequestException;
import com.englishacademy.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${SENDGRID_API_KEY}")
    private String sendGridKey;

    @Async
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
            log.info("HTTP STATUS CODE: " + response.getStatusCode());
            log.info(request.getBody());
            if (response.getStatusCode() != 202) {
                log.error("Failed to send email to {}. Status: {}, Response: {}", to, response.getStatusCode(), response.getBody());
                throw new RuntimeException("SendGrid error: " + response.getBody());
            }
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}