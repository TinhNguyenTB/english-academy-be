package com.englishacademy.controller;

import com.englishacademy.dto.request.UploadRequest;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.UploadResponse;
import com.englishacademy.service.UploadService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Validated
public class UploadController {
    UploadService uploadService;

    @PostMapping
    public ResponseData<UploadResponse> uploadFile(@ModelAttribute @Valid UploadRequest request) {
        UploadResponse response = uploadService.upload(
                request.getFile(),
                request.getType() != null ? request.getType() : "auto"
        );
        return ResponseData.<UploadResponse>builder()
                .code(HttpStatus.OK.value())
                .data(response)
                .message("Upload files")
                .build();
    }

    @PostMapping("/delete")
    public ResponseData<String> deleteFile(
            @RequestParam @NotBlank String publicId,
            @RequestParam @NotBlank String type
    ) {
        String result = uploadService.delete(publicId, type);
        return ResponseData.<String>builder()
                .code(HttpStatus.OK.value())
                .data(result)
                .message(result.equals("ok") ? "Delete successful" : "Delete failed")
                .build();
    }
}
