package com.englishacademy.service.impl;

import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.entity.QuestionOption;
import com.englishacademy.mapper.QuestionOptionMapper;
import com.englishacademy.repository.QuestionOptionRepository;
import com.englishacademy.service.QuestionOptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private QuestionOptionRepository questionOptionRepository;
    private QuestionOptionMapper questionOptionMapper;

    public QuestionOptionServiceImpl(QuestionOptionRepository questionOptionRepository, QuestionOptionMapper questionOptionMapper) {
        this.questionOptionRepository = questionOptionRepository;
        this.questionOptionMapper = questionOptionMapper;
    }

    @Override
    public void createQuestionOption(QuestionOptionRequestDTO questionOptionRequestDTO) {
        questionOptionRepository.save(questionOptionMapper.mapToEntity(questionOptionRequestDTO));
    }

    @Override
    public void updateQuestionOption(Long id, QuestionOptionRequestDTO questionOptionRequestDTO) {
        QuestionOption questionOption = questionOptionRepository.findById(id).get();
        questionOptionMapper.updateEntity(questionOptionRequestDTO, questionOption);
        questionOptionRepository.save(questionOption);
    }

    @Override
    public void deleteQuestionOption(Long id) {
        questionOptionRepository.deleteById(id);
    }

    @Override
    public Page<QuestionOption> getAllQuestionOptions(Pageable pageable) {
        return questionOptionRepository.findAll(pageable);
    }

    @Override
    public Page<QuestionOption> getQuestionOptionsByQuestionId(Long questionId, Pageable pageable) {
        return questionOptionRepository.findByQuestion_Id(questionId, pageable);

    }

    @Override
    public Page<QuestionOption> getQuestionOptionsByIsCorrect(Boolean isCorrect, Pageable pageable) {
        return questionOptionRepository.findByIsCorrect(isCorrect, pageable);
    }

}
