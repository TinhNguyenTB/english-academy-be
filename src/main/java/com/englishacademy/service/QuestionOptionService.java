package com.englishacademy.service;

import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.entity.QuestionOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionOptionService {
    void createQuestionOption(QuestionOptionRequestDTO questionOptionRequestDTO);

    void updateQuestionOption(Long id, QuestionOptionRequestDTO questionOptionRequestDTO);

    void deleteQuestionOption(Long id);

    Page<QuestionOption> getAllQuestionOptions(Pageable pageable);

    QuestionOption getQuestionOptionsByQuestionId(Long questionId);

    Page<QuestionOption> getQuestionOptionsByIsCorrect(Boolean isCorrect, Pageable pageable);

}
