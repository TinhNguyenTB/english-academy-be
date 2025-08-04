package com.englishacademy.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class MeaningTextValidator implements ConstraintValidator<MeaningText, String> {

    private int minLength;
    private int mindiffChars;

    @Override
    public void initialize(MeaningText constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.mindiffChars = constraintAnnotation.mindiffChars();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.isEmpty() || s == null){
            return true;
        }

        //length
        if(s.trim().length() < minLength){
            return false;
        }

        //diffChars
        if(s.length() >= minLength){
            int numberDiffChars = 0;
            Set<Character> charSet = new HashSet<Character>();
            for(int i = 0; i < mindiffChars; i++){
                charSet.add(s.charAt(i));
            }
            numberDiffChars = charSet.size();
            if(numberDiffChars < mindiffChars){
                return false;
            }
            return true;
        }
        return false;
    }
}
