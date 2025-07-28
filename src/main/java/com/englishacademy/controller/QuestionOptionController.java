package com.englishacademy.controller;

import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.entity.QuestionOption;
import com.englishacademy.service.QuestionOptionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/question-options")
public class QuestionOptionController {

    private QuestionOptionService questionOptionService;

    public QuestionOptionController(QuestionOptionService questionOptionService) {
        this.questionOptionService = questionOptionService;
    }

    @GetMapping("/get")
    public Page<QuestionOption> getQuestionOptions(Pageable pageable) {
        return questionOptionService.getAllQuestionOptions(pageable);
    }

    @PostMapping("/create")
    public void createQuestionOption(@Valid @RequestBody QuestionOptionRequestDTO questionOption) {
        questionOptionService.createQuestionOption(questionOption);
    }

    @PostMapping("/update/{id}")
    public void updateQuestionOption(@PathVariable Long id,@Valid @RequestBody QuestionOptionRequestDTO questionOption) {
        questionOptionService.updateQuestionOption(id, questionOption);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestionOption(@PathVariable Long id) {
        questionOptionService.deleteQuestionOption(id);
    }

    @GetMapping("/get-by-questionid")
    public Page<QuestionOption> getQuestionOptionsByQuestionId(@NotBlank @RequestParam Long questionId, Pageable pageable) {
        return questionOptionService.getQuestionOptionsByQuestionId(questionId, pageable);
    }

    @GetMapping("/get-by-iscorrect")
    public Page<QuestionOption> getQuestionOptionsByIsCorrect(@NotBlank @RequestParam Boolean isCorrect, Pageable pageable) {
        return questionOptionService.getQuestionOptionsByIsCorrect(isCorrect, pageable);
    }

}
