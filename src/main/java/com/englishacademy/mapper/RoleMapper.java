package com.englishacademy.mapper;


import com.englishacademy.dto.request.RoleRequest;
import com.englishacademy.dto.response.RoleResponse;
import com.englishacademy.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role role);
}
