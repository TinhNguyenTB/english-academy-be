package com.englishacademy.controller;

import com.englishacademy.dto.request.RoleRequest;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.RoleResponse;
import com.englishacademy.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ResponseData<RoleResponse> createRole(@RequestBody @Valid RoleRequest request){
        return ResponseData.<RoleResponse>builder()
                .data(roleService.create(request))
                .message("Create a new role")
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @GetMapping
    public ResponseData<Object> findAll(
            @PageableDefault(page = 0, size = 10)
            Pageable pageable){
        return ResponseData.<Object>builder()
                .code(HttpStatus.OK.value())
                .data(roleService.getAll(pageable))
                .message("Get all roles")
                .build();
    }
    @PutMapping("/{name}")
    public ResponseData<RoleResponse> update(@PathVariable String name, @RequestBody RoleRequest request){
        RoleResponse response = roleService.update(name, request);
        return ResponseData.<RoleResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Update role:"+name)
                .data(response)
                .build();
    }
    @DeleteMapping("/{name}")
    public ResponseData<Object> delete(@PathVariable String name){
        roleService.delete(name);
        return ResponseData.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Deleted role:"+name)
                .build();
    }
}
