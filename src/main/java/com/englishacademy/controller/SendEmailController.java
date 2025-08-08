package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.exception.BadRequestException;
import com.englishacademy.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class SendEmailController {

    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @PostMapping("/send-welcome")
    public ResponseData<String> welcome(@RequestBody List<String> emailList ) {
        if(emailList.size() == 0) {
            throw new BadRequestException("Email is null or empty");
        }
        Context context = new Context();
        String content = templateEngine.process("email-welcome", context);
        String subject = "Chào mừng đến với English Academy";

        for(String email : emailList) {
            emailService.sendEmail(email, subject, content);
        }

        return ResponseData.<String>builder()
              .message(Translator.toLocale("email.send.success"))
              .code(HttpStatus.OK.value())
              .build();
    }
}
