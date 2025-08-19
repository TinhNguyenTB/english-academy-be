package com.englishacademy.controller;

import com.englishacademy.utils.OTPUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email/test")
@RequiredArgsConstructor
public class TestEmailController {
    private final OTPUtils otpUtils;

    @GetMapping("/welcome")
    public String sendEmail() {
        return "email-welcome";
    }

    @GetMapping("/confirm")
    public String confirmEmail(Model model) {
        String otp = otpUtils.generateOTP();
        model.addAttribute("otp", otp);
        return "email-confirm";
    }
}
