package com.englishacademy.dto.response;

import com.englishacademy.enums.QuestionType;
import lombok.Data;

@Data
public class QuestionResponseDTO {
    private Long id;
    private QuestionType questionType;
    private String prompt;
    private String correctAnswer;
    private String mediaUrl;
    private String explanation;
}
