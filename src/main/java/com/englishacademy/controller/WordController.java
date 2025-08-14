package com.englishacademy.controller;

import com.englishacademy.dto.request.WordRequest;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.WordResponse;
import com.englishacademy.service.WordService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("words")
public class WordController {
    WordService wordService;

    @PostMapping
    public ResponseData<WordResponse> create(@RequestBody @Valid WordRequest request) {
        WordResponse response = wordService.create(request);
        return ResponseData.<WordResponse>builder()
                .message("Create a new word")
                .code(HttpStatus.CREATED.value())
                .data(response)
                .build();
    }
    @GetMapping("/{id}")
    public ResponseData<WordResponse> getById(@PathVariable Long id){
        WordResponse response = wordService.getById(id);
        return ResponseData.<WordResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get word details")
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseData<Object> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<WordResponse> response = wordService.getAll(pageable);
        return ResponseData.builder()
                .message("Get all words")
                .data(response)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<WordResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid WordRequest request) {
        WordResponse response = wordService.update(id, request);
        return ResponseData.<WordResponse>builder()
                .code(HttpStatus.CREATED.value())
                .data(response)
                .message("Update word")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteById (@PathVariable Long id){
        wordService.delete(id);
        return ResponseData.<Void>builder()
                .message("Delete word")
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }
}
