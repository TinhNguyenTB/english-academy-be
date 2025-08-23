package com.englishacademy.service.impl;

import com.englishacademy.dto.request.PermissionRequest;
import com.englishacademy.dto.response.PermissionResponse;
import com.englishacademy.entity.Permission;
import com.englishacademy.mapper.PermissionMapper;
import com.englishacademy.repository.PermissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceImplTest {
    @Mock
    private PermissionRepository permissionRepository;
    @Mock
    private PermissionMapper permissionMapper;
    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Test
    public void testCreate() {
        PermissionRequest request = new PermissionRequest("", "");
        request.setName("TEST_PERMISSION");

        Permission permission = new Permission();
        permission.setName("TEST_PERMISSION");

        when(permissionMapper.toEntity(request)).thenReturn(permission);

        permissionService.create(request);
        verify(permissionRepository).save(any(Permission.class));
    }

    @Test
    public void testGetAll() {
        Pageable pageable = Pageable.unpaged();
        Page<Permission> permissionPage = Page.empty();
        when(permissionRepository.findAll(pageable)).thenReturn(permissionPage);
        permissionService.getAll(pageable);

        verify(permissionRepository).findAll(pageable);
    }

    @Test
    public void testDelete() {
        String permissionName = "TEST_PERMISSION";
        permissionService.delete(permissionName);

        verify(permissionRepository).deleteById(permissionName);
    }
}
