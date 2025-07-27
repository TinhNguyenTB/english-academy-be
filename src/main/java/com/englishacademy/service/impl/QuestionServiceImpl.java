package com.englishacademy.service.impl;

import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import com.englishacademy.mapper.QuestionMapper;
import com.englishacademy.repository.QuestionRepository;
import com.englishacademy.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestionsByLessonId(Long lessonId, Pageable pageable) {
        return questionRepository.findQuestionByLessonId(lessonId, pageable);
    }

    @Override
    public Page<Question> getQuestionsByQuestionType(QuestionType questionType, Pageable pageable) {
        return questionRepository.findQuestionByQuestionType(questionType, pageable);
    }

    @Override
    public Page<Question> getQuestionsByOptionType(OptionType optionType, Pageable pageable) {
        return questionRepository.findQuestionByOptionType(optionType, pageable);
    }

    @Override
    public Page<Question> getQuestionsByPrompt(String prompt, Pageable pageable) {
        return questionRepository.findQuestionByPromptContaining(prompt, pageable);
    }

    @Override
    public Page<Question> getQuestionsByCorrectAnswer(String correctAnswer, Pageable pageable) {
        return questionRepository.findQuestionByCorrectAnswerContaining(correctAnswer, pageable);
    }

    @Override
    public Question createQuestion(QuestionRequestDTO question) {
        return questionRepository.save(questionMapper.mapToEntity(question));
    }

    @Override
    public Question updateQuestion(Long id, QuestionRequestDTO question) {
        Question questionEntity = questionRepository.findById(id).orElse(null);
        if(questionEntity != null) {
            questionMapper.updateEntity(question, questionEntity);
            return questionRepository.save(questionEntity);
        }
        return null;
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Question> getQuestionsByLessonIdAndQuestionType(Long lessonId, QuestionType questionType, Pageable pageable) {
        return questionRepository.findQuestionByLessonIdAndQuestionType(lessonId, questionType, pageable);
    }
}
