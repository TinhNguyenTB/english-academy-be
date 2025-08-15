package com.englishacademy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageDTO {
    private String to;
    private String subject;
    private String body;
    private int retryNumber = 0;
    private LocalDateTime lastRetryTime;
    private LocalDateTime createAt;
}
