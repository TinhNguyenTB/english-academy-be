package com.englishacademy.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonProgressResponse {
    Long id;
    Boolean isCompleted;
    LocalDateTime completedAt;
}
