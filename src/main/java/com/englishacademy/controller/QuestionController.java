package com.englishacademy.controller;

import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import com.englishacademy.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/get")
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionService.getAllQuestions(pageable);
    }

    @PostMapping("/create")
    public Question createQuestion(@Valid @RequestBody QuestionRequestDTO question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/update/{id}")
    public Question updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionRequestDTO question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/lesson/{lessonId}")
    public Page<Question> getQuestionsByLessonId(@PathVariable Long lessonId, Pageable pageable) {
        return questionService.getByLessonId(lessonId, pageable);
    }

    @GetMapping("/type/{questionType}")
    public Page<Question> getQuestionsByQuestionType(@NotNull @PathVariable QuestionType questionType, Pageable pageable) {
        return questionService.getQuestionsByQuestionType(questionType, pageable);
    }

    @GetMapping("/option/{optionType}")
    public Page<Question> getQuestionsByOptionType(@NotNull @PathVariable OptionType optionType, Pageable pageable) {
        return questionService.getQuestionsByOptionType(optionType, pageable);
    }

    @GetMapping("/prompt")
    public Page<Question> getQuestionsByPrompt(@NotBlank @RequestParam String prompt, Pageable pageable) {
        return questionService.getQuestionsByPrompt(prompt, pageable);
    }

    @GetMapping("/correct-answer")
    public Page<Question> getQuestionsByCorrectAnswer(@NotBlank @RequestParam String correctAnswer, Pageable pageable) {
        return questionService.getQuestionsByCorrectAnswer(correctAnswer, pageable);
    }

    @GetMapping("/lesson/{lessonId}/type/{questionType}")
    public Page<Question> getQuestionsByLessonIdAndQuestionType(@PathVariable Long lessonId, @PathVariable QuestionType questionType,Pageable pageable) {
        return questionService.getQuestionsByLessonIdAndQuestionType(lessonId, questionType, pageable);
    }
}
