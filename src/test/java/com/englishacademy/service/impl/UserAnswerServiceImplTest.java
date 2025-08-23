package com.englishacademy.service.impl;

import com.englishacademy.dto.request.UserAnswerRequestDTO;
import com.englishacademy.entity.Question;
import com.englishacademy.entity.UserAnswer;
import com.englishacademy.mapper.UserAnswerMapper;
import com.englishacademy.repository.QuestionRepository;
import com.englishacademy.repository.UserAnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAnswerServiceImplTest {
    @Mock
    private UserAnswerRepository userAnswerRepository;
    @Mock
    private UserAnswerMapper userAnswerMapper;
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private UserAnswerServiceImpl userAnswerServiceImpl;

    @Test
    void testCreateUserAnswer() {
        UserAnswerRequestDTO userAnswerRequestDTO = new UserAnswerRequestDTO();
        userAnswerRequestDTO.setQuestionId(1L);

        Question question = new Question();
        question.setId(1L);

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(userAnswerMapper.toUserAnswer(userAnswerRequestDTO)).thenReturn(new UserAnswer());

        userAnswerServiceImpl.createUserAnswer(userAnswerRequestDTO);

        verify(userAnswerMapper).toUserAnswer(userAnswerRequestDTO);
        verify(userAnswerRepository).save(Mockito.any(UserAnswer.class));
    }

    @Test
    void testUpdateUserAnswer() {
        UserAnswerRequestDTO userAnswerRequestDTO = new UserAnswerRequestDTO();
        userAnswerRequestDTO.setQuestionId(1L);

        UserAnswer existingUserAnswer = new UserAnswer();
        existingUserAnswer.setId(1L);

        when(userAnswerRepository.findById(1L)).thenReturn(Optional.of(existingUserAnswer));

        userAnswerServiceImpl.updateUserAnswer(1L, userAnswerRequestDTO);

        verify(userAnswerMapper).updateUserAnswerFromRequestDTO(userAnswerRequestDTO, existingUserAnswer);
        verify(userAnswerRepository).save(existingUserAnswer);
    }

    @Test
    void testDeleteUserAnswer() {
        Long userAnswerId = 1L;
        userAnswerServiceImpl.deleteUserAnswer(userAnswerId);
        verify(userAnswerRepository).deleteById(userAnswerId);
    }

    @Test
    void testGetUserAnswerById() {
        Page<UserAnswer> userAnswerPage = Mockito.mock(Page.class);
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setId(1L);
        when(userAnswerRepository.findById(1L)).thenReturn(Optional.of(userAnswer));
        userAnswerServiceImpl.getUserAnswerById(1L);
        verify(userAnswerRepository).findById(1L);


    }

    @Test
    void testGetUserAnswerByUserIdAndQuestionId() {
        Long userId = 1L;
        Long questionId = 1L;
        userAnswerServiceImpl.getUserAnswerByUserIdAndQuestionId(userId, questionId);
        verify(userAnswerRepository).findByUserIdAndQuestionId(userId, questionId);
    }

    @Test
    void testGetUserAnswerByUserId() {
        Long userId = 1L;
        userAnswerServiceImpl.getUserAnswerByUserId(userId, null);
        verify(userAnswerRepository).findAllByUserId(userId, null);
    }

    @Test
    void testGetUserAnswerByQuestionId() {
        Long questionId = 1L;
        userAnswerServiceImpl.getUserAnswerByQuestionId(questionId, null);
        verify(userAnswerRepository).findAllByQuestionId(questionId, null);
    }

    @Test
    void testGetUserAnswerByIsCorrect() {
        boolean isCorrect = true;
        userAnswerServiceImpl.getUserAnswerByIsCorrect(isCorrect, null);
        verify(userAnswerRepository).findAllByIsCorrect(isCorrect, null);
    }

    @Test
    void testGetUserAnswerByAnsweredAtBetween() {
        userAnswerServiceImpl.getUserAnswerByAnsweredAtBetween(null, null, null);
        verify(userAnswerRepository).findAllByAnsweredAtBetween(null, null, null);
    }

    @Test
    void testGetUserAnswerByUserIdAndIsCorrect() {
        Long userId = 1L;
        boolean isCorrect = true;
        userAnswerServiceImpl.getUserAnswerByUserIdAndIsCorrect(userId, isCorrect, null);
        verify(userAnswerRepository).findAllByUserIdAndIsCorrect(userId, isCorrect, null);
    }

    @Test
    void testExistsByUserIdAndQuestionId() {
        Long userId = 1L;
        Long questionId = 1L;
        userAnswerServiceImpl.existsByUserIdAndQuestionId(userId, questionId);
        verify(userAnswerRepository).existsByUserIdAndQuestionId(userId, questionId);
    }

    @Test
    void testGetAllUserAnswers() {
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<UserAnswer> userAnswerPage = Mockito.mock(Page.class);
        when(userAnswerRepository.findAll(pageable)).thenReturn(userAnswerPage);
        userAnswerServiceImpl.getAllUserAnswers(pageable);
        verify(userAnswerRepository).findAll(pageable);

    }

}
