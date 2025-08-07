package com.englishacademy.dto.response;

import lombok.Data;

@Data
public class LessonResponeDTO {
    private Long id;
    private String code;
    private String name;
    private int orderIndex;
    private int totalQuestion;
}
