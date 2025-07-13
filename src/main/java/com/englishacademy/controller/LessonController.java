package com.englishacademy.controller;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.service.LessonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/get-all")
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/get/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/create")
    public void createLesson(@Valid @RequestBody LessonRequestDTO lessonRequestDTO) {
        lessonService.createLesson(lessonRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @PutMapping("/update/{id}")
    public void updateLesson(@PathVariable Long id, @Valid  @RequestBody  LessonRequestDTO lessonRequestDTO) {
        lessonService.updateLesson(id, lessonRequestDTO);
    }

    @GetMapping("/find-by-name")
    public List<Lesson> findByName(@RequestParam @NotBlank String name) {
        return lessonService.findByName(name);
    }

    @GetMapping("/find-by-topic-id")
    public List<Lesson> findByTopicId(@RequestParam @NotNull Long topicId) {
        return lessonService.findByTopicId(topicId);
    }

    @GetMapping("/find-by-order-index")
    public List<Lesson> findByOrderIndex(@RequestParam @Min(1) int orderIndex) {
        return lessonService.findByOrderIndex(orderIndex);
    }

    @GetMapping("/find-by-total-question")
    public List<Lesson> findByTotalQuestion(@RequestParam @Min(0) int totalQuestion) {
        return lessonService.findByTotalQuestion(totalQuestion);
    }


}
