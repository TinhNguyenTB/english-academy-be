package com.englishacademy.exception;

public record ValidationError(String field, String message) {
}
