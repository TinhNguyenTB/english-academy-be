package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import com.englishacademy.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/get")
    public ResponseData<Page<Question>> getAllQuestions(Pageable pageable) {
        Page<Question> questions = questionService.getAllQuestions(pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.get.all.success"))
                .data(questions)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<Question> createQuestion(@Valid @RequestBody QuestionRequestDTO question) {
        Question q = questionService.createQuestion(question);
        return ResponseData.<Question>builder()
                .message(Translator.toLocale("question.create.success"))
                .data(q)
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseData<Question> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionRequestDTO question) {
        Question q = questionService.updateQuestion(id, question);
        return ResponseData.<Question>builder()
                .message(Translator.toLocale("question.update.success"))
                .data(q)
                .code(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("question.delete.success"))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<Question> getQuestionById(@PathVariable Long id) {
        Question q =  questionService.getQuestionById(id);
        return ResponseData.<Question>builder()
                .message(Translator.toLocale("question.find.by.id.success"))
                .data(q)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseData<Page<Question>> getQuestionsByLessonId(@PathVariable Long lessonId, Pageable pageable) {
        Page<Question> questionPage = questionService.getByLessonId(lessonId, pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.find.by.lesson.id.success"))
                .data(questionPage)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/type/{questionType}")
    public ResponseData<Page<Question>> getQuestionsByQuestionType(@NotNull @PathVariable QuestionType questionType, Pageable pageable) {
        Page<Question> questionPage = questionService.getQuestionsByQuestionType(questionType, pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.find.by.questionType.success"))
                .data(questionPage)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/option/{optionType}")
    public ResponseData<Page<Question>> getQuestionsByOptionType(@NotNull @PathVariable OptionType optionType, Pageable pageable) {
        Page<Question> questions = questionService.getQuestionsByOptionType(optionType, pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.find.by.optionType.success"))
                .data(questions)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/prompt")
    public ResponseData<Page<Question>> getQuestionsByPrompt(@NotBlank @RequestParam String prompt, Pageable pageable) {
        Page<Question> questions = questionService.getQuestionsByPrompt(prompt, pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.find.by.prompt.success"))
                .data(questions)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/correct-answer")
    public ResponseData<Page<Question>> getQuestionsByCorrectAnswer(@NotBlank @RequestParam String correctAnswer, Pageable pageable) {
        Page<Question> questions = questionService.getQuestionsByCorrectAnswer(correctAnswer, pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.find.by.correctAnswer.success"))
                .data(questions)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/lesson/{lessonId}/type/{questionType}")
    public ResponseData<Page<Question>> getQuestionsByLessonIdAndQuestionType(@PathVariable Long lessonId, @PathVariable QuestionType questionType,Pageable pageable) {
        Page<Question> questions = questionService.getQuestionsByLessonIdAndQuestionType(lessonId, questionType, pageable);
        return ResponseData.<Page<Question>>builder()
                .message(Translator.toLocale("question.find.by.lesson.id.and.questionType.success"))
                .data(questions)
                .code(HttpStatus.OK.value())
                .build();
    }
}
