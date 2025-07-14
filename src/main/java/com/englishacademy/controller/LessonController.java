package com.englishacademy.controller;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.service.LessonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping("/get")
    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonService.getAllLessons(pageable);
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/create")
    public void createLesson(@Valid @RequestBody LessonRequestDTO lessonRequestDTO) {
        lessonService.createLesson(lessonRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @PutMapping("/{id}")
    public void updateLesson(@PathVariable Long id, @Valid  @RequestBody  LessonRequestDTO lessonRequestDTO) {
        lessonService.updateLesson(id, lessonRequestDTO);
    }

    @GetMapping("/find-name")
    public Page<Lesson> findByName(@RequestParam @NotBlank String name, Pageable pageable) {
        return lessonService.findByName(name, pageable);
    }

    @GetMapping("/find-topic")
    public Page<Lesson> findByTopicId(@RequestParam @NotNull Long topicId, Pageable pageable) {
        return lessonService.findByTopicId(topicId, pageable);
    }

    @GetMapping("/find-order")
    public Page<Lesson> findByOrderIndex(@RequestParam @Min(1) int orderIndex, Pageable pageable) {
        return lessonService.findByOrderIndex(orderIndex, pageable);
    }

    @GetMapping("/find-total")
    public Page<Lesson> findByTotalQuestion(@RequestParam @Min(0) int totalQuestion, Pageable pageable) {
        return lessonService.findByTotalQuestion(totalQuestion, pageable);
    }


}
