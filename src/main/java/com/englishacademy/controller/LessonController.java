package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.dto.response.LessonResponeDTO;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.UserResponse;
import com.englishacademy.entity.Lesson;
import com.englishacademy.service.LessonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/lessons")
public class LessonController {

    private LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping("/get")
    public ResponseData<Page<LessonResponeDTO>> getAllLessons(Pageable pageable) {
        Page<LessonResponeDTO> lessons = lessonService.getAllLessons(pageable);
        return ResponseData.<Page<LessonResponeDTO>>builder()
                .message(Translator.toLocale("lesson.get.all.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<LessonResponeDTO> getLessonById(@PathVariable Long id) {
        LessonResponeDTO lesson = lessonService.getLessonById(id);
        return ResponseData.<LessonResponeDTO>builder()
                .message(Translator.toLocale("lesson.get.by.id.success"))
                .data(lesson)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<LessonResponeDTO> createLesson(@Valid @RequestBody LessonRequestDTO lessonRequestDTO) {
        LessonResponeDTO lessonResponeDTO = lessonService.createLesson(lessonRequestDTO);
        return ResponseData.<LessonResponeDTO>builder()
                .message(Translator.toLocale(Translator.toLocale("lesson.create.success")))
                .code(HttpStatus.CREATED.value())
                .data(lessonResponeDTO)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("lesson.delete.success"))
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<LessonResponeDTO> updateLesson(@PathVariable Long id, @Valid  @RequestBody  LessonRequestDTO lessonRequestDTO) {
        LessonResponeDTO lessonResponeDTO = lessonService.updateLesson(id, lessonRequestDTO);
        return ResponseData.<LessonResponeDTO>builder()
                .message(Translator.toLocale("lesson.update.success"))
                .code(HttpStatus.OK.value())
                .data(lessonResponeDTO)
                .build();
    }

    @GetMapping("/find-name")
    public ResponseData<Page<LessonResponeDTO>> findByName(@RequestParam @NotBlank String name, Pageable pageable) {
        Page<LessonResponeDTO> lessons = lessonService.findByName(name, pageable);
        return ResponseData.<Page<LessonResponeDTO>>builder()
                .message(Translator.toLocale("lesson.find.by.name.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-topic")
    public ResponseData<Page<LessonResponeDTO>> findByTopicId(@RequestParam @NotNull Long topicId, Pageable pageable) {
        Page<LessonResponeDTO> lessons = lessonService.findByTopicId(topicId, pageable);
        return ResponseData.<Page<LessonResponeDTO>>builder()
                .message(Translator.toLocale("lesson.find.by.topicId.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-order")
    public ResponseData<Page<LessonResponeDTO>> findByOrderIndex(@RequestParam @Min(1) int orderIndex, Pageable pageable) {
        Page<LessonResponeDTO> lessons = lessonService.findByOrderIndex(orderIndex, pageable);
        return ResponseData.<Page<LessonResponeDTO>>builder()
                .message(Translator.toLocale("lesson.find.by.orderIndex.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-total")
    public ResponseData<Page<LessonResponeDTO>> findByTotalQuestion(@RequestParam @Min(0) int totalQuestion, Pageable pageable) {
        Page<LessonResponeDTO> lessons = lessonService.findByTotalQuestion(totalQuestion, pageable);
        return ResponseData.<Page<LessonResponeDTO>>builder()
                .message(Translator.toLocale("lesson.find.by.totalQuestion.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }


}
