package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionOptionRequestDTO {

    @NotNull(message = "không được để trống questionId")
    private Long questionId;

    @NotBlank(message = "không được để trống prompt")
    private String prompt;

    @NotBlank(message = "không được để trống optionText")
    private String optionText;

    private boolean isCorrect;

    @Size(max = 500, message = "imageUrl không được quá 500 ký tự")
    private String imageUrl;

    @Size(max = 500, message = "audioUrl không được quá 500 ký tự")
    private String audioUrl;
}
