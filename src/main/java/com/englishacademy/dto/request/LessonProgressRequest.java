package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonProgressRequest {
    @NotNull(message = "userId must not be null")
    Long userId;

    @NotNull(message = "lessonId must not be null")
    Long lessonId;

    Boolean isCompleted;
    LocalDateTime completedAt;
}
