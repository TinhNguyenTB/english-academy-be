package com.englishacademy.service.impl;

import com.englishacademy.dto.request.UserAnswerRequestDTO;
import com.englishacademy.entity.UserAnswer;
import com.englishacademy.mapper.UserAnswerMapper;
import com.englishacademy.repository.UserAnswerRepository;
import com.englishacademy.service.UserAnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    private UserAnswerRepository userAnswerRepository;
    private UserAnswerMapper userAnswerMapper;

    public UserAnswerServiceImpl(UserAnswerRepository userAnswerRepository, UserAnswerMapper userAnswerMapper) {
        this.userAnswerRepository = userAnswerRepository;
        this.userAnswerMapper = userAnswerMapper;
    }

    @Override
    public void  createUserAnswer(UserAnswerRequestDTO userAnswerRequestDTO) {
        userAnswerRepository.save(userAnswerMapper.toUserAnswer(userAnswerRequestDTO));
    }

    @Override
    public void updateUserAnswer(Long id, UserAnswerRequestDTO userAnswerRequestDTO) {
        UserAnswer userAnswer = userAnswerRepository.findById(id).orElseThrow();
        userAnswerMapper.updateUserAnswerFromRequestDTO(userAnswerRequestDTO, userAnswer);
        userAnswerRepository.save(userAnswer);
    }

    @Override
    public void deleteUserAnswer(Long id) {
        userAnswerRepository.deleteById(id);
    }

    @Override
    public UserAnswer getUserAnswerById(Long id) {
        return userAnswerRepository.findById(id).orElseThrow();
    }

    @Override
    public UserAnswer getUserAnswerByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.findByUserIdAndQuestionId(userId, questionId);
    }

    @Override
    public Page<UserAnswer> getUserAnswerByUserId(Long userId, Pageable pageable) {
        return userAnswerRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<UserAnswer> getUserAnswerByQuestionId(Long questionId, Pageable pageable) {
        return userAnswerRepository.findAllByQuestionId(questionId, pageable);
    }

    @Override
    public Page<UserAnswer> getUserAnswerByIsCorrect(boolean isCorrect, Pageable pageable) {
        return userAnswerRepository.findAllByIsCorrect(isCorrect, pageable);
    }

    @Override
    public Page<UserAnswer> getUserAnswerByAnsweredAtBetween(Timestamp start, Timestamp end, Pageable pageable) {
        return userAnswerRepository.findAllByAnsweredAtBetween(start, end, pageable);
    }

    @Override
    public Page<UserAnswer> getUserAnswerByUserIdAndIsCorrect(Long userId, boolean isCorrect, Pageable pageable) {
        return userAnswerRepository.findAllByUserIdAndIsCorrect(userId, isCorrect, pageable);
    }

    @Override
    public boolean existsByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.existsByUserIdAndQuestionId(userId, questionId);
    }

    @Override
    public UserAnswer findByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.findByUserIdAndQuestionId(userId, questionId);
    }

    @Override
    public Page<UserAnswer> getAllUserAnswers(Pageable pageable) {
        return userAnswerRepository.findAll(pageable);
    }

    @Override
    public Page<UserAnswer> getUserAnswerBySelectedAnswer(String selectedAnswer, Pageable pageable) {
        return userAnswerRepository.findAllBySelectedAnswer(selectedAnswer, pageable);
    }
}
