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
    @NotNull(message = "{lesson.progress.userId.not.null}")
    Long userId;

    @NotNull(message = "{lesson.progress.lessonId.not.null}")
    Long lessonId;

    Boolean isCompleted;
    LocalDateTime completedAt;
}
