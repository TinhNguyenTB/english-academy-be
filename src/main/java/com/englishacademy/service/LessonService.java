package com.englishacademy.service;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;

import java.util.List;

public interface LessonService {

    Lesson getLessonById(Long id);

    List<Lesson> getAllLessons();

    void createLesson(LessonRequestDTO lesson);

    void deleteLesson(Long id);

    void updateLesson(Long id, LessonRequestDTO lesson);

    List<Lesson> findByName(String name);

    List<Lesson> findByTopicId(Long topicId);

    List<Lesson> findByOrderIndex(int orderIndex);

    List<Lesson> findByTotalQuestion(int totalQuestion);




}
