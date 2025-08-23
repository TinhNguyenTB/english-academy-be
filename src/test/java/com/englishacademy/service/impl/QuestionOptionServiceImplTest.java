package com.englishacademy.service.impl;

import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.entity.Question;
import com.englishacademy.entity.QuestionOption;
import com.englishacademy.mapper.QuestionOptionMapper;
import com.englishacademy.repository.QuestionOptionRepository;
import com.englishacademy.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionOptionServiceImplTest {
    @Mock
    private QuestionOptionRepository questionOptionRepository;
    @Mock
    private QuestionOptionMapper questionOptionMapper;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionOption questionOption;
    @InjectMocks
    private QuestionOptionServiceImpl questionOptionServiceImpl;

    @Test
    public void testCreateQuestionOption() {
        QuestionOptionRequestDTO questionOptionRequestDTO = new QuestionOptionRequestDTO();
        questionOptionRequestDTO.setQuestionId(1L);

        Question question = new Question();
        question.setId(1L);

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionOptionMapper.mapToEntity(questionOptionRequestDTO)).thenReturn(questionOption);

        questionOptionServiceImpl.createQuestionOption(questionOptionRequestDTO);

        verify(questionOptionMapper).mapToEntity(questionOptionRequestDTO);
        verify(questionOption).setQuestion(question);
        verify(questionOptionRepository).save(questionOption);
    }

   @Test
    public void testUpdateQuestionOption() {
        QuestionOption oldQuestionOption = new QuestionOption();
        oldQuestionOption.setId(1L);
        QuestionOptionRequestDTO questionOptionRequestDTO = new QuestionOptionRequestDTO();
        questionOptionRequestDTO.setQuestionId(1L);

        when(questionOptionRepository.findById(1L)).thenReturn(Optional.of(oldQuestionOption));
        when(questionOptionRepository.save(oldQuestionOption)).thenReturn(oldQuestionOption);

        questionOptionServiceImpl.updateQuestionOption(1L, questionOptionRequestDTO);
        verify(questionOptionRepository).save(oldQuestionOption);

    }

    @Test
    public void testDeleteQuestionOption() {
        Long id = 1L;
        questionOptionServiceImpl.deleteQuestionOption(id);
        verify(questionOptionRepository).deleteById(id);
    }

    @Test
    public void testGetAllQuestionOptions() {
        Page<QuestionOption> questionOptionPage = new PageImpl<>(Arrays.asList(questionOption));
        when(questionOptionRepository.findAll(org.springframework.data.domain.PageRequest.of(0, 10)))
                .thenReturn(questionOptionPage);
        Page<QuestionOption> result = questionOptionServiceImpl.getAllQuestionOptions(org.springframework.data.domain.PageRequest.of(0, 10));
        verify(questionOptionRepository).findAll(org.springframework.data.domain.PageRequest.of(0, 10));
        assert result.getContent().size() == 1;
        assert result.getContent().get(0).equals(questionOption);
    }

    @Test
    public void testGetQuestionOptionsByQuestionId() {
        Long questionId = 1L;
        when(questionOptionRepository.findByQuestion_Id(questionId)).thenReturn(questionOption);

        QuestionOption result = questionOptionServiceImpl.getQuestionOptionsByQuestionId(questionId);

        verify(questionOptionRepository).findByQuestion_Id(questionId);
        assert result.equals(questionOption);
    }

    @Test
    public void testGetQuestionOptionsByIsCorrect() {
        Boolean isCorrect = true;
        Page<QuestionOption> questionOptionPage = new PageImpl<>(Arrays.asList(questionOption));
        when(questionOptionRepository.findByIsCorrect(isCorrect, org.springframework.data.domain.PageRequest.of(0, 10)))
                .thenReturn(questionOptionPage);

        Page<QuestionOption> result = questionOptionServiceImpl.getQuestionOptionsByIsCorrect(isCorrect, org.springframework.data.domain.PageRequest.of(0, 10));

        verify(questionOptionRepository).findByIsCorrect(isCorrect, org.springframework.data.domain.PageRequest.of(0, 10));
        assert result.getContent().size() == 1;
        assert result.getContent().get(0).equals(questionOption);
    }

}
