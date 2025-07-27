package com.englishacademy.dto.request;

import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionRequestDTO {

    @NotNull(message = "không được để trống lessonId")
    private Long lessonId;

    @NotNull(message = "không được để trống loại câu hỏi")
    private QuestionType type;

    @NotBlank(message = "không được để trống câu hỏi")
    private String prompt;

    @NotNull(message = "không được để trống loại đáp án")
    private OptionType optionType;

    @NotBlank(message = "không được để trống đáp án đúng")
    private String correctAnswer;

    @Size(max = 300, message = "url không được quá 300 ký tự")
    private String mediaUrl;

    @Size(max = 500, message = "giải thích không được quá 500 ký tự")
    private String explanation;
}
