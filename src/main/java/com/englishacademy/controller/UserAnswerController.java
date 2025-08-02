package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.UserAnswerRequestDTO;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.entity.UserAnswer;
import com.englishacademy.service.UserAnswerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Validated
@RestController
@RequestMapping("/user-answers")
public class UserAnswerController {

    private UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @GetMapping("/get")
    public ResponseData<Page<UserAnswer>> getAllUserAnswers(Pageable pageable) {
        Page<UserAnswer> userAnswers = userAnswerService.getAllUserAnswers(pageable);
        return ResponseData.<Page<UserAnswer>>builder()
                .message(Translator.toLocale("user.answer.get.all.success"))
                .data(userAnswers)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<Void> createUserAnswer(@Valid @RequestBody UserAnswerRequestDTO userAnswerRequestDTO) {
        userAnswerService.createUserAnswer(userAnswerRequestDTO);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("user.answer.create.success"))
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<Void> updateUserAnswer(@PathVariable Long id, @Valid @RequestBody UserAnswerRequestDTO userAnswerRequestDTO) {
        userAnswerService.updateUserAnswer(id, userAnswerRequestDTO);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("user.answer.update.success"))
                .code(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteUserAnswer(@PathVariable Long id) {
        userAnswerService.deleteUserAnswer(id);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("user.answer.delete.success"))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<UserAnswer> getUserAnswerById(@PathVariable Long id) {
        UserAnswer userAnswer = userAnswerService.getUserAnswerById(id);
        return ResponseData.<UserAnswer>builder()
                .message(Translator.toLocale("user.answer.find.by.id.success"))
                .data(userAnswer)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/user/{userId}")
    public ResponseData<Page<UserAnswer>> getUserAnswerByUserId(@NotNull @PathVariable Long userId, Pageable pageable) {
        Page<UserAnswer> userAnswers = userAnswerService.getUserAnswerByUserId(userId, pageable);
        return ResponseData.<Page<UserAnswer>>builder()
                .message(Translator.toLocale("user.answer.find.by.user.id.success"))
                .data(userAnswers)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/question/{questionId}")
    public ResponseData<Page<UserAnswer>> getUserAnswerByQuestionId(@NotNull @PathVariable Long questionId, Pageable pageable) {
        Page<UserAnswer> userAnswers = userAnswerService.getUserAnswerByQuestionId(questionId, pageable);
        return ResponseData.<Page<UserAnswer>>builder()
                .message(Translator.toLocale("user.answer.find.by.question.id.success"))
                .data(userAnswers)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/is-correct")
    public ResponseData<Page<UserAnswer>>  getUserAnswerByIsCorrect(@RequestParam boolean isCorrect, Pageable pageable) {
        Page<UserAnswer> userAnswers = userAnswerService.getUserAnswerByIsCorrect(isCorrect, pageable);
        return ResponseData.<Page<UserAnswer>>builder()
                .message(Translator.toLocale("user.answer.find.by.is.correct"))
                .data(userAnswers)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/answered-at")
    public ResponseData<Page<UserAnswer>>  getUserAnswerByAnsweredAtBetween(@RequestParam String start, @RequestParam String end,
                                                             Pageable pageable) {
       try{
           LocalDateTime startTimestamp = LocalDateTime.parse(start);
           LocalDateTime endTimestamp = LocalDateTime.parse(end);
           Page<UserAnswer> userAnswers = userAnswerService.getUserAnswerByAnsweredAtBetween(startTimestamp, endTimestamp, pageable);
              return ResponseData.<Page<UserAnswer>>builder()
                     .message(Translator.toLocale("user.answer.find.by.answeredat.between"))
                     .data(userAnswers)
                     .code(HttpStatus.OK.value())
                     .build();
       } catch (IllegalArgumentException e){
              throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.", e);
       }

    }

    @GetMapping("/user-is-correct")
    public ResponseData<Page<UserAnswer>>  getUserAnswerByUserIdAndIsCorrect(@NotNull @RequestParam Long userId, @RequestParam boolean isCorrect,
                                                              Pageable pageable) {
        Page<UserAnswer> userAnswers = userAnswerService.getUserAnswerByUserIdAndIsCorrect(userId, isCorrect, pageable);
        return ResponseData.<Page<UserAnswer>>builder()
                .message(Translator.toLocale("user.answer.find.by.user.id.and.is.correct"))
                .data(userAnswers)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/exists")
    public ResponseData<Boolean> existsByUserIdAndQuestionId(@NotNull @RequestParam Long userId,@NotNull @RequestParam Long questionId) {
       userAnswerService.existsByUserIdAndQuestionId(userId, questionId);
        return ResponseData.<Boolean>builder()
                .message(Translator.toLocale("user.answer.exists.by.user.id.and.question.id"))
                .data(true)
                .code(HttpStatus.OK.value())
                .build();

    }

    @GetMapping("/selected-answer")
    public ResponseData<Page<UserAnswer>> getUserAnswerBySelectedAnswer(@NotBlank @RequestParam String selectedAnswer, Pageable pageable) {
        Page<UserAnswer>  userAnswers =  userAnswerService.getUserAnswerBySelectedAnswer(selectedAnswer, pageable);
        return ResponseData.<Page<UserAnswer>>builder()
                .message(Translator.toLocale("user.answer.find.by.selected.answer.success"))
                .data(userAnswers)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/userId-and-questionId")
    public ResponseData<UserAnswer> getUserAnswerByUserIdAndQuestionId(@NotNull @RequestParam Long userId, @NotNull @RequestParam Long questionId) {
        UserAnswer userAnswer = userAnswerService.getUserAnswerByUserIdAndQuestionId(userId, questionId);
        return ResponseData.<UserAnswer>builder()
                .message(Translator.toLocale("user.answer.find.by.user.id.and.question.id.success"))
                .data(userAnswer)
                .code(HttpStatus.OK.value())
                .build();
    }
}
