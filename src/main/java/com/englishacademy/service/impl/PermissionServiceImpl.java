package com.englishacademy.service.impl;

import com.englishacademy.dto.request.PermissionRequest;
import com.englishacademy.dto.response.PermissionResponse;
import com.englishacademy.mapper.PermissionMapper;
import com.englishacademy.entity.Permission;
import com.englishacademy.repository.PermissionRepository;
import com.englishacademy.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @CachePut(value = "PERMISSION_CACHE", key = "#result.name")
    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toEntity(request);
        Permission permissionSaved = permissionRepository.save(permission);
        return permissionMapper.toResponse(permissionSaved);
    }

    @Override
    public Page<PermissionResponse> getAll(Pageable pageable) {
        return permissionRepository.findAll(pageable)
                .map(permissionMapper::toResponse);
    }

    @CacheEvict(value = "PERMISSION_CACHE", key = "#name")
    @Override
    public void delete(String name) {
        permissionRepository.deleteById(name);
    }
}
