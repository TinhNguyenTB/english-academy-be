package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonProgressRequest {
    @NotBlank(message = "userId must not be blank")
    Long userId;

    @NotBlank(message = "lessonId must not be blank")
    Long lessonId;

    Boolean isCompleted;
    LocalDateTime completedAt;
}
