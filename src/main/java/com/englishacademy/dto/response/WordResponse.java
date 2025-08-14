package com.englishacademy.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordResponse {
    Long id;
    Long topicId;
    String word;
    String meaning;
    String example;
    String pronunciation;
    String audioUrl;
    String imageUrl;
}
