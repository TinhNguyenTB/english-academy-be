package com.englishacademy.repository;

import com.englishacademy.entity.QuestionOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    QuestionOption findByQuestion_Id(Long questionId);
    Page<QuestionOption> findByIsCorrect(Boolean Correct, Pageable pageable);
}
