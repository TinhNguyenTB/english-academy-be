package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.UserRequest;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.UserResponse;
import com.englishacademy.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseData<Page<UserResponse>> getAllUsers(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        Page<UserResponse> users = userService.getAllUsers(pageable);

        return ResponseData.<Page<UserResponse>>builder()
                .data(users)
                .message("Get all users")
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping
    public ResponseData<UserResponse> createUser(@Valid @RequestBody UserRequest dto) {
        UserResponse response = userService.createUser(dto);
        return ResponseData.<UserResponse>builder()
                .message(Translator.toLocale("user.create.success"))
                .data(response)
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<UserResponse> updateUser(@Valid @RequestBody UserRequest dto, @PathVariable Long id) {
        UserResponse response = userService.updateUser(id, dto);
        return ResponseData.<UserResponse>builder()
                .message(Translator.toLocale("user.update.success"))
                .data(response)
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseData<UserResponse> getUserById(@Min(1) @PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseData.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(Translator.toLocale("user.get.success"))
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
