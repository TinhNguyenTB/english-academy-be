package com.englishacademy.service.impl;

import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.dto.response.QuestionResponseDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.entity.Question;
import com.englishacademy.enums.QuestionType;
import com.englishacademy.mapper.QuestionMapper;
import com.englishacademy.repository.LessonRepository;
import com.englishacademy.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionMapper questionMapper;
    @Mock
    private LessonRepository lessonRepository;
    @InjectMocks
    private QuestionServiceImpl questionServiceImpl;

    @Test
    void testGetAllQuestions() {
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findAll(pageable)).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getAllQuestions(pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findAll(pageable);
    }

    @Test
    void testGetQuestionsByQuestionType() {
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findByType(any(), eq(pageable))).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getQuestionsByQuestionType(null, pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findByType(any(), eq(pageable));
    }

    @Test
    void testGetQuestionsByOptionType() {
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findByOptionType(any(), eq(pageable))).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getQuestionsByOptionType(null, pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findByOptionType(any(), eq(pageable));
    }

    @Test
    void testGetQuestionsByPrompt() {
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findByPromptContaining(any(), eq(pageable))).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getQuestionsByPrompt("test", pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findByPromptContaining(any(), eq(pageable));
    }

    @Test
    void testGetQuestionsByCorrectAnswer() {
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findByCorrectAnswerContaining(any(), eq(pageable))).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getQuestionsByCorrectAnswer("answer", pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findByCorrectAnswerContaining(any(), eq(pageable));
    }

    @Test
    void testCreateQuestion() {
        Question question = new Question();
        question.setId(1L);
        question.setPrompt("Sample Question");

        QuestionRequestDTO requestDTO = new QuestionRequestDTO();
        requestDTO.setLessonId(1L);

        Lesson lesson = new Lesson();
        lesson.setId(1L);

        when(lessonRepository.findById(1L)).thenReturn(java.util.Optional.of(lesson));
        when(questionMapper.mapToEntity(requestDTO)).thenReturn(question);
        when(questionRepository.save(any())).thenReturn(question);

        Question result = questionServiceImpl.createQuestion(requestDTO);

        assertEquals(1L, result.getId());
        assertEquals("Sample Question", result.getPrompt());
        verify(lessonRepository).findById(1L);
        verify(questionMapper).mapToEntity(requestDTO);
        verify(questionRepository).save(any());
    }

    @Test
    void testUpdateQuestion_success() {
        Question oldQuestion = new Question();
        oldQuestion.setId(1L);
        QuestionResponseDTO responseDTO = new QuestionResponseDTO();
        responseDTO.setId(1L);
        QuestionRequestDTO requestDTO = new QuestionRequestDTO();
        requestDTO.setLessonId(1L);

        when(questionRepository.findById(1L)).thenReturn(Optional.of(oldQuestion));
        questionServiceImpl.updateQuestion(1L, requestDTO);
        verify(questionRepository).findById(1L);
        verify(questionMapper).updateEntity(requestDTO, oldQuestion);
        verify(questionRepository).save(oldQuestion);

    }

    @Test
    void testUpdateQuestion_empty() {
        QuestionRequestDTO requestDTO = new QuestionRequestDTO();
        requestDTO.setLessonId(1L);

        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        Question result = questionServiceImpl.updateQuestion(1L, requestDTO);

        assertEquals(null, result);
        verify(questionRepository).findById(1L);
        verify(questionMapper, never()).updateEntity(any(), any());
        verify(questionRepository, never()).save(any());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;

        questionServiceImpl.deleteQuestion(questionId);

        verify(questionRepository, times(1)).deleteById(questionId);
    }

    @Test
    void testGetQuestionById() {
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        Question result = questionServiceImpl.getQuestionById(questionId);

        assertEquals(questionId, result.getId());
        verify(questionRepository).findById(questionId);
    }

    @Test
    void testGetQuestionsByLessonIdAndQuestionType() {
        Long lessonId = 1L;
        QuestionType questionType = QuestionType.MULTIPLE_CHOICE;
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findByLesson_IdAndType(lessonId, questionType, pageable)).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getQuestionsByLessonIdAndQuestionType(lessonId, questionType, pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findByLesson_IdAndType(lessonId, questionType, pageable);
    }

    @Test
    void testGetByLessonId() {
        Long lessonId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Question question = new Question();
        question.setId(1L);
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findByLesson_Id(lessonId, pageable)).thenReturn(questionPage);

        Page<Question> result = questionServiceImpl.getByLessonId(lessonId, pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findByLesson_Id(lessonId, pageable);
    }



}
