package com.englishacademy.service;

import com.englishacademy.dto.response.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    UploadResponse upload(MultipartFile file, String resourceType);
    String delete(String publicId, String resourceType);
}
