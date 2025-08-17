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
    @NotNull(message = "{flash.card.userId.not.null}")
    @Min(value = 1, message = "{flash.card.userId.must.be.greater.than.or.equal.to.one}")
    Long userId;

    @NotNull(message = "{flash.card.wordId.not.null}")
    @Min(value = 1, message = "{flash.card.wordId.must.be.greater.than.or.equal.to.one}")
    Long wordId;
}
