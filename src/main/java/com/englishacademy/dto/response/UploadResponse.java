package com.englishacademy.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UploadResponse {
    String publicId;
    String url;
    String resourceType;
}
