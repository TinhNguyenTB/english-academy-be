package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class UserAnswerRequestDTO {

    @NotNull(message = "không được để trống userId")
    private Long userId;

    @NotNull(message = "không được để trống questionId")
    private Long questionId;

    @NotEmpty(message = "không được để trống selectedAnswer")
    private String selectedAnswer;

    private boolean isCorrect;

    @NotNull(message = "không được để trống answeredAt")
    private Timestamp answeredAt;

}
