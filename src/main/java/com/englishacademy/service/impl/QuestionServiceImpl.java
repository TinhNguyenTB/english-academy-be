package com.englishacademy.service.impl;

import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.entity.Question;
import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import com.englishacademy.mapper.QuestionMapper;
import com.englishacademy.repository.LessonRepository;
import com.englishacademy.repository.QuestionRepository;
import com.englishacademy.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;
    private LessonRepository lessonRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper, LessonRepository lessonRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestionsByQuestionType(QuestionType questionType, Pageable pageable) {
        return questionRepository.findByType(questionType, pageable);
    }

    @Override
    public Page<Question> getQuestionsByOptionType(OptionType optionType, Pageable pageable) {
        return questionRepository.findByOptionType(optionType, pageable);
    }

    @Override
    public Page<Question> getQuestionsByPrompt(String prompt, Pageable pageable) {
        return questionRepository.findByPromptContaining(prompt, pageable);
    }

    @Override
    public Page<Question> getQuestionsByCorrectAnswer(String correctAnswer, Pageable pageable) {
        return questionRepository.findByCorrectAnswerContaining(correctAnswer, pageable);
    }

    @Override
    public Question createQuestion(QuestionRequestDTO questionRequestDTO) {
        Long lessonId = questionRequestDTO.getLessonId();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Question question = questionMapper.mapToEntity(questionRequestDTO);
        question.setLesson(lesson);
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long id, QuestionRequestDTO question) {
        Question questionEntity = questionRepository.findById(id).orElse(null);
        if (questionEntity != null) {
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
        return questionRepository.findByLesson_IdAndType(lessonId, questionType, pageable);
    }

    @Override
    public Page<Question> getByLessonId(Long lessonId, Pageable pageable) {
        return questionRepository.findByLesson_Id(lessonId, pageable);
    }

}
