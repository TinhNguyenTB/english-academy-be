package com.englishacademy.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto<T> {
    int status;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;
}
