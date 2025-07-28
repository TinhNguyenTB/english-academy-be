package com.englishacademy.service.impl;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.mapper.LessonMapper;
import com.englishacademy.repository.LessonRepository;
import com.englishacademy.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable);
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
    public Page<Lesson> findByName(String name, Pageable pageable) {
        return lessonRepository.findByName(name, pageable);
    }

    @Override
    public Page<Lesson> findByTopicId(Long topicId, Pageable pageable) {
        return lessonRepository.findByTopicId(topicId, pageable);
    }

    @Override
    public Page<Lesson> findByOrderIndex(int orderIndex, Pageable pageable) {
        return lessonRepository.findByOrderIndex(orderIndex, pageable);
    }

    @Override
    public Page<Lesson> findByTotalQuestion(int totalQuestion, Pageable pageable) {
        return lessonRepository.findByTotalQuestion(totalQuestion, pageable);
    }

}
