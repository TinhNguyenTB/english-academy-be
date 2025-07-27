package com.englishacademy.mapper;


import com.englishacademy.dto.request.PermissionRequest;
import com.englishacademy.dto.response.PermissionResponse;
import com.englishacademy.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends GenericMapper<PermissionRequest, PermissionResponse, Permission> {
}
