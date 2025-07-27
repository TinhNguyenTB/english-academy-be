package com.englishacademy.service;

import com.englishacademy.dto.request.PermissionRequest;
import com.englishacademy.dto.response.PermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    Page<PermissionResponse> getAll(Pageable pageable);
    void delete(String name);
}
