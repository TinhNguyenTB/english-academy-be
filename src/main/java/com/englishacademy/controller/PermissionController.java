package com.englishacademy.controller;


import com.englishacademy.dto.request.PermissionRequest;
import com.englishacademy.dto.response.PermissionResponse;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ResponseData<PermissionResponse> create(@RequestBody @Valid PermissionRequest request){
        PermissionResponse response = permissionService.create(request);
        return ResponseData.<PermissionResponse>builder()
                .code(HttpStatus.CREATED.value())
                .data(response)
                .message("Create a new permission")
                .build();
    }

    @GetMapping
    ResponseData<Object> getAll(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable){
        return ResponseData.builder()
                .code(HttpStatus.OK.value())
                .message("Get all permissions")
                .data(permissionService.getAll(pageable))
                .build();
    }

    @DeleteMapping("/{permission}")
    public ResponseData<Object> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ResponseData.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Deleted permission:"+permission)
                .build();
    }

}
