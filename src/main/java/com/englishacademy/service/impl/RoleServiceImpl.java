package com.englishacademy.service.impl;

import com.englishacademy.dto.request.RoleRequest;
import com.englishacademy.dto.response.RoleResponse;
import com.englishacademy.entity.Role;
import com.englishacademy.mapper.RoleMapper;
import com.englishacademy.repository.PermissionRepository;
import com.englishacademy.repository.RoleRepository;
import com.englishacademy.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleResponse create(RoleRequest request){
        Role role = roleMapper.toEntity(request);
        if (request.getPermissions() != null) {
            var permissions = permissionRepository.findAllById(request.getPermissions());
            role.setPermissions(new HashSet<>(permissions));
        }

        roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public Page<RoleResponse> getAll(Pageable pageable){
        return roleRepository.findAll(pageable)
                .map(roleMapper::toResponse);
    }

    @Override
    public RoleResponse update(String name, RoleRequest request) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));

        role.setDescription(request.getDescription());
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        Role updated = roleRepository.save(role);
        return roleMapper.toResponse(updated);
    }

    @Override
    public void delete(String name){
        roleRepository.deleteById(name);
    }
}
