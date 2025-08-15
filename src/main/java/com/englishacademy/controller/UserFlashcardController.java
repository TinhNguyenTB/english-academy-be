package com.englishacademy.controller;

import com.englishacademy.dto.request.UserFlashcardRequest;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.UserFlashcardResponse;
import com.englishacademy.service.UserFlashcardService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("user-flashcard")
public class UserFlashcardController {
    UserFlashcardService userFlashcardService;

    @GetMapping
    public ResponseData<Object> getAll(Pageable pageable){
        return ResponseData.builder()
                .data(userFlashcardService.getAll(pageable))
                .message("Get all user flashcards")
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping
    public ResponseData<UserFlashcardResponse> create(@RequestBody @Valid UserFlashcardRequest request){
        UserFlashcardResponse response = userFlashcardService.create(request);
        return ResponseData.<UserFlashcardResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create a new flashcard")
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<UserFlashcardResponse> update(
            @PathVariable Long id,
            @RequestBody UserFlashcardRequest request){
        UserFlashcardResponse response = userFlashcardService.update(id, request);
        return ResponseData.<UserFlashcardResponse>builder()
                .data(response)
                .message("Update flashcard")
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteById(@PathVariable Long id){
        userFlashcardService.delete(id);
        return ResponseData.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Delete flashcard")
                .build();
    }
}
