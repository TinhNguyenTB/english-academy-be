package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.exception.BadRequestException;
import com.englishacademy.service.EmailProducerKafka;
import com.englishacademy.service.EmailService;
import com.englishacademy.utils.OTPUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Log4j2
@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class SendEmailController {

    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final EmailProducerKafka emailProducer;
    private final OTPUtils otpUtils;

    @PostMapping("/send-welcome")
    public ResponseData<String> welcome(@RequestBody List<String> emailList ) {
        if(emailList.size() == 0) {
            throw new BadRequestException("Email is null or empty");
        }
        Context context = new Context();
        String content = templateEngine.process("email-welcome", context);
        String subject = "Chào mừng đến với English Academy";

        for(String email : emailList) {
            EmailMessageDTO dto = new EmailMessageDTO(email, subject, content, 0, LocalDateTime.now(), LocalDateTime.now());
            emailProducer.sendEmailToKafka(dto);
        }

        return ResponseData.<String>builder()
              .message(Translator.toLocale("email.send.success"))
              .code(HttpStatus.OK.value())
              .build();
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> receiveWebhook(@RequestBody List<Map<String, Object>> events) {
        log.info("Received webhook: {}", events);
        emailService.handleWebhookEvents(events);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-confirm")
    public ResponseData<String> confirm(@RequestBody List<String> emailList) {
        String otp = otpUtils.generateOTP();

        if(emailList.size() == 0) {
            throw new BadRequestException("Email is null or empty");
        }

        Context context = new Context();
        context.setVariable("otp", otp);
        String content = templateEngine.process("email-confirm", context);
        String subject = "Xác nhận";

        for(String email : emailList) {
            EmailMessageDTO dto = new EmailMessageDTO(email, subject, content, 0, LocalDateTime.now(), LocalDateTime.now());
            emailProducer.sendEmailToKafka(dto);
        }

        return ResponseData.<String>builder()
                .message(Translator.toLocale("email.send.success"))
                .code(HttpStatus.OK.value())
                .build();
    }
}
