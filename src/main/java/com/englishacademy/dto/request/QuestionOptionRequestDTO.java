package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionOptionRequestDTO {

    @NotNull(message = "{question.option.questionId.not.null}")
    private Long questionId;

    @NotBlank(message = "{question.option.prompt.not.blank}")
    private String prompt;

    @NotBlank(message = "{question.option.optionText.not.blank}")
    private String optionText;

    private boolean isCorrect;

    @Size(max = 500, message = "{question.option.imageUrl.size.limit}")
    private String imageUrl;

    @Size(max = 500, message = "{question.option.audioUrl.size.limit}")
    private String audioUrl;
}
