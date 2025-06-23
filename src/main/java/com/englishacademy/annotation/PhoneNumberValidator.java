package com.englishacademy.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_PATTERN = "^(0\\d{9}|\\+?[1-9]\\d{7,14})$";
    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN);

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        return PATTERN.matcher(phoneNumber).matches();
    }
}
