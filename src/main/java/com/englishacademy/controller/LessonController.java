package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.LessonRequestDTO;
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
    public ResponseData<Page<Lesson>> getAllLessons(Pageable pageable) {
        Page<Lesson> lessons = lessonService.getAllLessons(pageable);
        return ResponseData.<Page<Lesson>>builder()
                .message(Translator.toLocale("lesson.get.all.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<Lesson> getLessonById(@PathVariable Long id) {
        Lesson lesson = lessonService.getLessonById(id);
        return ResponseData.<Lesson>builder()
                .message(Translator.toLocale("lesson.get.by.id.success"))
                .data(lesson)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<Void> createLesson(@Valid @RequestBody LessonRequestDTO lessonRequestDTO) {
        lessonService.createLesson(lessonRequestDTO);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale(Translator.toLocale("lesson.create.success")))
                .code(HttpStatus.CREATED.value())
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
    public ResponseData<Void> updateLesson(@PathVariable Long id, @Valid  @RequestBody  LessonRequestDTO lessonRequestDTO) {
        lessonService.updateLesson(id, lessonRequestDTO);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("lesson.update.success"))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-name")
    public ResponseData<Page<Lesson>> findByName(@RequestParam @NotBlank String name, Pageable pageable) {
        Page<Lesson> lessons = lessonService.findByName(name, pageable);
        return ResponseData.<Page<Lesson>>builder()
                .message(Translator.toLocale("lesson.find.by.name.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-topic")
    public ResponseData<Page<Lesson>> findByTopicId(@RequestParam @NotNull Long topicId, Pageable pageable) {
        Page<Lesson> lessons = lessonService.findByTopicId(topicId, pageable);
        return ResponseData.<Page<Lesson>>builder()
                .message(Translator.toLocale("lesson.find.by.topicId.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-order")
    public ResponseData<Page<Lesson>> findByOrderIndex(@RequestParam @Min(1) int orderIndex, Pageable pageable) {
        Page<Lesson> lessons = lessonService.findByOrderIndex(orderIndex, pageable);
        return ResponseData.<Page<Lesson>>builder()
                .message(Translator.toLocale("lesson.find.by.orderIndex.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/find-total")
    public ResponseData<Page<Lesson>> findByTotalQuestion(@RequestParam @Min(0) int totalQuestion, Pageable pageable) {
        Page<Lesson> lessons = lessonService.findByTotalQuestion(totalQuestion, pageable);
        return ResponseData.<Page<Lesson>>builder()
                .message(Translator.toLocale("lesson.find.by.totalQuestion.success"))
                .data(lessons)
                .code(HttpStatus.OK.value())
                .build();
    }


}
