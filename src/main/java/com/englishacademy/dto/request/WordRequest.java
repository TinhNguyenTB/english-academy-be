package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRequest {
    @NotNull(message = "topicId must not be null")
    Long topicId;

    @NotBlank(message = "word must not be blank")
    String word;

    @NotBlank(message = "meaning must not be blank")
    String meaning;

    String example;

    @NotBlank(message = "pronunciation must not be blank")
    String pronunciation;

    String audioUrl;

    String imageUrl;
}
