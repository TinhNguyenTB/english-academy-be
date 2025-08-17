package com.englishacademy.dto.request;

import jakarta.validation.constraints.Min;
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
    @NotNull(message = "{word.topicId.not.null}")
    @Min(value = 1, message = "{word.topicId.must.be.greater.than.or.equal.to.one}")
    Long topicId;

    @NotBlank(message = "{word.not.be.blank}")
    String word;

    @NotBlank(message = "{word.meaning.not.be.blank}")
    String meaning;

    String example;

    @NotBlank(message = "{word.pronunciation.not.be.blank}")
    String pronunciation;

    String audioUrl;

    String imageUrl;
}
