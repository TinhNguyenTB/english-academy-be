package com.englishacademy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvTestController {
    @Value("${SENDGRID_API_KEY}")
    private String apiKey;

    @GetMapping("/test-env")
    public String testEnv() {
        return "API Key = " + apiKey;
    }
}


