package com.englishacademy.repository;

import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findQuestionByLessonId(Long lessonId, Pageable pageable);
    Page<Question> findQuestionByQuestionType(QuestionType questionType, Pageable pageable);
    Page<Question> findQuestionByOptionType(OptionType optionType, Pageable pageable);
    Page<Question> findQuestionByPromptContaining(String prompt, Pageable pageable);
    Page<Question> findQuestionByCorrectAnswerContaining(String correctAnswer, Pageable pageable);
    Page<Question> findQuestionByLessonIdAndQuestionType(Long lessonId, QuestionType questionType, Pageable pageable);
}
