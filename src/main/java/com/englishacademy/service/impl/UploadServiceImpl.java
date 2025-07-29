package com.englishacademy.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.englishacademy.dto.response.UploadResponse;
import com.englishacademy.service.UploadService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    Cloudinary cloudinary;

    @Override
    public UploadResponse upload(MultipartFile file, String resourceType) {
        try {
            String publicId = String.valueOf(Instant.now().toEpochMilli());
            Map<String, Object> options = ObjectUtils.asMap(
                    "public_id", publicId,
                    "overwrite", true,
                    "resource_type", resourceType
            );

            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), options);

            return new UploadResponse(
                    publicId,
                    result.get("secure_url").toString(),
                    resourceType
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to Cloudinary", e);
        }
    }

    @Override
    public String delete(String publicId, String resourceType) {
        try {
            Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap(
                    "resource_type", resourceType
            ));
            return result.get("result").toString(); // "ok" nếu xoá thành công
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file from Cloudinary", e);
        }
    }
}
