package com.englishacademy.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFlashcardResponse {
    Long id;
    Long userId;
    Long wordId;
}
