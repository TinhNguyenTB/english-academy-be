package com.englishacademy.dto.request;

import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionRequestDTO {

    @NotNull(message = "{question.lessonId.not.null}")
    private Long lessonId;

    @NotNull(message = "{question.type.not.null}")
    private QuestionType type;

    @NotBlank(message = "{question.prompt.not.blank}")
    private String prompt;

    @NotNull(message = "{question.optionType.not.null}")
    private OptionType optionType;

    @NotBlank(message = "{question.correctAnswer.not.blank}")
    private String correctAnswer;

    @Size(max = 300, message = "{question.mediaUrl.size.limit}")
    private String mediaUrl;

    @Size(max = 500, message = "{question.explanation.size.limit}")
    private String explanation;
}
