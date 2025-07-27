package com.englishacademy.service;

import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Page<Question> getAllQuestions(Pageable pageable);

    Page<Question> getQuestionsByLessonId(Long lessonId, Pageable pageable);

    Page<Question> getQuestionsByQuestionType(QuestionType questionType, Pageable pageable);

    Page<Question> getQuestionsByOptionType(OptionType optionType, Pageable pageable);

    Page<Question> getQuestionsByPrompt(String prompt, Pageable pageable);

    Page<Question> getQuestionsByCorrectAnswer(String correctAnswer, Pageable pageable);

    Question createQuestion(QuestionRequestDTO question);

    Question updateQuestion(Long id, QuestionRequestDTO question);

    void deleteQuestion(Long id);

    Question getQuestionById(Long id);

    Page<Question> getQuestionsByLessonIdAndQuestionType(Long lessonId, QuestionType questionType, Pageable pageable);

}
