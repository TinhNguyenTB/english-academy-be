package com.englishacademy.repository;

import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByLesson_Id(Long lessonId, Pageable pageable);
    Page<Question> findByType(QuestionType type, Pageable pageable);
    Page<Question> findByOptionType(OptionType optionType, Pageable pageable);
    Page<Question> findByPromptContaining(String prompt, Pageable pageable);
    Page<Question> findByCorrectAnswerContaining(String correctAnswer, Pageable pageable);
    Page<Question> findByLesson_IdAndType(Long lessonId, QuestionType type, Pageable pageable);
}
