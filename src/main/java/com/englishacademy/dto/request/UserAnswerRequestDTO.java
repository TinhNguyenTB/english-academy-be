package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class UserAnswerRequestDTO {

    @NotNull(message = "{user.answer.userId.not.null}")
    private Long userId;

    @NotNull(message = "{user.answer.questionId.not.null}")
    private Long questionId;

    @NotEmpty(message = "{user.answer.selectedAnswer.not.empty}")
    private String selectedAnswer;

    private boolean isCorrect;

    @NotNull(message = "{user.answer.answeredAt.not.null}")
    private Timestamp answeredAt;

}
