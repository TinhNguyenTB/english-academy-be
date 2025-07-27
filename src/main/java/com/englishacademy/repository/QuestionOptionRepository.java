package com.englishacademy.repository;

import com.englishacademy.entity.QuestionOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    Page<QuestionOption> findByQuestionId(Long questionId, Pageable pageable);
    Page<QuestionOption> findByOptionType(String optionType, Pageable pageable);
    Page<QuestionOption> findByContentContaining(String content, Pageable pageable);
    Page<QuestionOption> findByIsCorrect(Boolean isCorrect, Pageable pageable);
    Page<QuestionOption> findByQuestionIdAndOptionType(Long questionId, String optionType, Pageable pageable);
}
