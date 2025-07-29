package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.entity.QuestionOption;
import com.englishacademy.service.QuestionOptionService;
import jakarta.transaction.Transaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/question-options")
public class QuestionOptionController {

    private QuestionOptionService questionOptionService;

    public QuestionOptionController(QuestionOptionService questionOptionService) {
        this.questionOptionService = questionOptionService;
    }

    @GetMapping("/get")
    public ResponseData<Page<QuestionOption>> getQuestionOptions(Pageable pageable) {
        Page<QuestionOption> questionOptions = questionOptionService.getAllQuestionOptions(pageable);
        return ResponseData.<Page<QuestionOption>>builder()
                .message(Translator.toLocale("question.option.get.all.success"))
                .data(questionOptions)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<Void> createQuestionOption(@Valid @RequestBody QuestionOptionRequestDTO questionOption) {
        questionOptionService.createQuestionOption(questionOption);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("question.option.create.success"))
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @PostMapping("/update/{id}")
    public ResponseData<Void>  updateQuestionOption(@PathVariable Long id,@Valid @RequestBody QuestionOptionRequestDTO questionOption) {
        questionOptionService.updateQuestionOption(id, questionOption);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("question.option.update.success"))
                .code(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<Void>  deleteQuestionOption(@PathVariable Long id) {
        questionOptionService.deleteQuestionOption(id);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("question.option.delete.success"))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/get-by-questionid")
    public ResponseData<Page<QuestionOption>> getQuestionOptionsByQuestionId(@NotBlank @RequestParam Long questionId, Pageable pageable) {
        Page<QuestionOption> questionOptions = questionOptionService.getQuestionOptionsByQuestionId(questionId, pageable);
        return ResponseData.<Page<QuestionOption>>builder()
                    .message(Translator.toLocale("question.option.find.by.question.id.success"))
                .data(questionOptions)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/get-by-iscorrect")
    public ResponseData<Page<QuestionOption>> getQuestionOptionsByIsCorrect(@NotBlank @RequestParam Boolean isCorrect, Pageable pageable) {
        Page<QuestionOption> questionOptions = questionOptionService.getQuestionOptionsByIsCorrect(isCorrect, pageable);
        return ResponseData.<Page<QuestionOption>>builder()
                .message(Translator.toLocale("question.option.find.by.is.correct.success"))
                .data(questionOptions)
                .code(HttpStatus.OK.value())
                .build();
    }

}
