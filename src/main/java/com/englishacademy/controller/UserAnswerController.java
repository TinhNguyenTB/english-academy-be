package com.englishacademy.controller;

import com.englishacademy.dto.request.UserAnswerRequestDTO;
import com.englishacademy.entity.UserAnswer;
import com.englishacademy.service.UserAnswerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Validated
@RestController
@RequestMapping("/api/user-answers")
public class UserAnswerController {

    private UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @GetMapping("/get")
    public Page<UserAnswer> getAllUserAnswers(Pageable pageable) {
        return userAnswerService.getAllUserAnswers(pageable);
    }

    @PostMapping("/create")
    public void createUserAnswer(@Valid @RequestBody UserAnswerRequestDTO userAnswerRequestDTO) {
        userAnswerService.createUserAnswer(userAnswerRequestDTO);
    }

    @PutMapping("/update/{id}")
    public void updateUserAnswer(@PathVariable Long id, @Valid @RequestBody UserAnswerRequestDTO userAnswerRequestDTO) {
        userAnswerService.updateUserAnswer(id, userAnswerRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserAnswer(@PathVariable Long id) {
        userAnswerService.deleteUserAnswer(id);
    }

    @GetMapping("/{id}")
    public UserAnswer getUserAnswerById(@PathVariable Long id) {
        return userAnswerService.getUserAnswerById(id);
    }

    @GetMapping("/user/{userId}")
    public Page<UserAnswer> getUserAnswerByUserId(@NotNull @PathVariable Long userId, Pageable pageable) {
        return userAnswerService.getUserAnswerByUserId(userId, pageable);
    }

    @GetMapping("/question/{questionId}")
    public Page<UserAnswer> getUserAnswerByQuestionId(@NotNull @PathVariable Long questionId, Pageable pageable) {
        return userAnswerService.getUserAnswerByQuestionId(questionId, pageable);
    }

    @GetMapping("/is-correct")
    public Page<UserAnswer> getUserAnswerByIsCorrect(@RequestParam boolean isCorrect, Pageable pageable) {
        return userAnswerService.getUserAnswerByIsCorrect(isCorrect, pageable);
    }

    @GetMapping("/answered-at")
    public Page<UserAnswer> getUserAnswerByAnsweredAtBetween(@RequestParam String start, @RequestParam String end,
                                                             Pageable pageable) {
       try{
           Timestamp startTimestamp = Timestamp.valueOf(start);
           Timestamp endTimestamp = Timestamp.valueOf(end);
           return userAnswerService.getUserAnswerByAnsweredAtBetween(startTimestamp, endTimestamp, pageable);
       } catch (IllegalArgumentException e){
              throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.", e);
       }

    }

    @GetMapping("/user-is-correct")
    public Page<UserAnswer> getUserAnswerByUserIdAndIsCorrect(@NotNull @RequestParam Long userId, @RequestParam boolean isCorrect,
                                                              Pageable pageable) {
        return userAnswerService.getUserAnswerByUserIdAndIsCorrect(userId, isCorrect, pageable);
    }

    @GetMapping("/exists")
    public boolean existsByUserIdAndQuestionId(@NotNull @RequestParam Long userId,@NotNull @RequestParam Long questionId) {
        return userAnswerService.existsByUserIdAndQuestionId(userId, questionId);
    }

    @GetMapping("/find")
    public UserAnswer findByUserIdAndQuestionId(@NotNull @RequestParam Long userId,@NotNull @RequestParam Long questionId) {
        return userAnswerService.findByUserIdAndQuestionId(userId, questionId);
    }

    @GetMapping("/selected-answer")
    public Page<UserAnswer> getUserAnswerBySelectedAnswer(@NotNull @RequestParam String selectedAnswer, Pageable pageable) {
        return userAnswerService.getUserAnswerBySelectedAnswer(selectedAnswer, pageable);
    }

    @GetMapping("/userId-and-questionId")
    public UserAnswer getUserAnswerByUserIdAndQuestionId(@NotNull @RequestParam Long userId, @NotNull @RequestParam Long questionId) {
        return userAnswerService.getUserAnswerByUserIdAndQuestionId(userId, questionId);
    }
}
