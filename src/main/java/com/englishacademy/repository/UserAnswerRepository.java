package com.englishacademy.repository;

import com.englishacademy.entity.UserAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    UserAnswer findByUserIdAndQuestionId(Long userId, Long questionId);

    boolean existsByUserIdAndQuestionId(Long userId, Long questionId);

    Page<UserAnswer> findAllByUserId(Long userId, Pageable pageable);

    Page<UserAnswer> findAllByQuestionId(Long questionId, Pageable pageable);

    Page<UserAnswer> findAllByIsCorrect(boolean Correct, Pageable pageable);

    Page<UserAnswer> findAllByAnsweredAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<UserAnswer> findAllByUserIdAndIsCorrect(Long userId, boolean Correct, Pageable pageable);

    Page<UserAnswer> findAllBySelectedAnswer(String selectedAnswer, Pageable pageable);

}
