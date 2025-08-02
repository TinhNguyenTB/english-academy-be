package com.englishacademy.controller;

import com.englishacademy.dto.request.LessonProgressRequest;
import com.englishacademy.dto.response.LessonProgressResponse;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.service.LessonProgressService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson-progress")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class LessonProgressController {
    LessonProgressService lessonProgressService;

    @GetMapping
    public ResponseData<Object> getAll (Pageable pageable){
        return ResponseData.builder()
                .message("Get all lesson progress")
                .data(lessonProgressService.getAll(pageable))
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping
    public ResponseData<LessonProgressResponse> create (@RequestBody @Valid LessonProgressRequest request){
        LessonProgressResponse response = lessonProgressService.create(request);
        return ResponseData.<LessonProgressResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create lesson progress")
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<LessonProgressResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid LessonProgressRequest request
    ){
     LessonProgressResponse response = lessonProgressService.update(id, request);
     return ResponseData.<LessonProgressResponse>builder()
             .code(HttpStatus.CREATED.value())
             .message("Update lesson progress")
             .data(response)
             .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteById (@PathVariable Long id){
        lessonProgressService.delete(id);
        return ResponseData.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Delete lesson progress successfully")
                .build();

    }
}
