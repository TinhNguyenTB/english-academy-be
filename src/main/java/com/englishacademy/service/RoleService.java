package com.englishacademy.service;

import com.englishacademy.dto.request.RoleRequest;
import com.englishacademy.dto.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    Page<RoleResponse> getAll(Pageable pageable);
    RoleResponse update(String name, RoleRequest request);
    void delete(String name);
}
