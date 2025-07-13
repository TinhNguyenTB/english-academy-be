package com.englishacademy.service.impl;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.mapper.LessonMapper;
import com.englishacademy.repository.LessonRepository;
import com.englishacademy.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;
    private LessonMapper lessonMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }


    @Override
    public void createLesson(LessonRequestDTO lesson) {
        lessonRepository.save(lessonMapper.toEntity(lesson));
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public void updateLesson(Long id, LessonRequestDTO lesson) {
        Lesson oldLesson = lessonRepository.findById(id).get();
        lessonMapper.updateEntityFromDto(lesson, oldLesson);
        lessonRepository.save(oldLesson);
    }

    @Override
    public List<Lesson> findByName(String name) {
        return lessonRepository.findByName(name);
    }

    @Override
    public List<Lesson> findByTopicId(Long topicId) {
        return lessonRepository.findByTopicId(topicId);
    }

    @Override
    public List<Lesson> findByOrderIndex(int orderIndex) {
        return lessonRepository.findByOrderIndex(orderIndex);
    }

    @Override
    public List<Lesson> findByTotalQuestion(int totalQuestion) {
        return lessonRepository.findByTotalQuestion(totalQuestion);
    }

}
