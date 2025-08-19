package com.englishacademy.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OTPUtils {

    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = random.nextInt(999999) + 100000;
        return String.valueOf(otp);
    }
}
