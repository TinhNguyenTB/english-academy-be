package com.englishacademy.utils;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OTPUtils {

    @CachePut(key = "#email", value = "otp")
    public String generateOTP(String email) {
        SecureRandom random = new SecureRandom();
        int otp = random.nextInt(999999) + 100000;
        return String.valueOf(otp);
    }

    @Cacheable(key = "#email", value = "otp", unless="#result == null")
    public String getOTP(String email) {
        return null;
    }

    @CacheEvict(key = "#email", value = "otp")
    public void deleteOTP(String email) {

    }
}
