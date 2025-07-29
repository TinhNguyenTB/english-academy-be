package com.englishacademy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LessonRequestDTO {

    @NotBlank(message = "{lesson.code.not.blank}")
    private String code;

    @NotNull(message = "{lesson.topicId.not.null}")
    private Long topicId;

    @NotBlank(message = "{lesson.name.not.blank}")
    private String name;

    @Min(value = 1, message = "{lesson.orderIndex.must.greater.or.equal.to.one}")
    private int orderIndex;

    @Min(value = 0, message = "{lesson.totalQuestion.must.greater.or.equal.to.zero}")
    private int totalQuestion;
}
