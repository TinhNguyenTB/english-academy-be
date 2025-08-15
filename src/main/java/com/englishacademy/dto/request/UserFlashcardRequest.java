package com.englishacademy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFlashcardRequest {
    @NotNull(message = "userId must not be null")
    @Min(value = 1, message = "userId must be greater than or equal to 1")
    Long userId;

    @NotNull(message = "wordId must not be null")
    @Min(value = 1, message = "wordId must be greater than or equal to 1")
    Long wordId;
}
