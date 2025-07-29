package com.englishacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UploadRequest {
    @NotNull(message = "file cannot be null")
    MultipartFile file;

    @NotBlank(message = "type cannot be blank")
    String type; // image | video | raw | auto
}
