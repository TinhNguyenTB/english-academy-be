package com.englishacademy.service;

import com.englishacademy.dto.request.UserAnswerRequestDTO;
import com.englishacademy.entity.UserAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;

public interface UserAnswerService {
    void createUserAnswer(UserAnswerRequestDTO userAnswerRequestDTO);

    void updateUserAnswer(Long id, UserAnswerRequestDTO userAnswerRequestDTO);

    void deleteUserAnswer(Long id);

    UserAnswer getUserAnswerById(Long id);

    UserAnswer getUserAnswerByUserIdAndQuestionId(Long userId, Long questionId);

    Page<UserAnswer> getUserAnswerByUserId(Long userId, Pageable pageable);

    Page<UserAnswer> getUserAnswerByQuestionId(Long questionId, Pageable pageable);

    Page<UserAnswer> getUserAnswerByIsCorrect(boolean isCorrect, Pageable pageable);

    Page<UserAnswer> getUserAnswerByAnsweredAtBetween(Timestamp start, Timestamp end, Pageable pageable);

    Page<UserAnswer> getUserAnswerByUserIdAndIsCorrect(Long userId, boolean isCorrect, Pageable pageable);

    boolean existsByUserIdAndQuestionId(Long userId, Long questionId);

    UserAnswer findByUserIdAndQuestionId(Long userId, Long questionId);

    Page<UserAnswer> getAllUserAnswers(Pageable pageable);

    Page<UserAnswer> getUserAnswerBySelectedAnswer(String selectedAnswer, Pageable pageable);
}
