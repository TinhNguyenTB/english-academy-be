package com.englishacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email/test")
public class TestEmailController {
    @GetMapping("/welcome")
    public String sendEmail() {
        return "email-welcome";
    }
}
