package com.englishacademy.service;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {

    Lesson getLessonById(Long id);

    Page<Lesson> getAllLessons(Pageable pageable);

    void createLesson(LessonRequestDTO lesson);

    void deleteLesson(Long id);

    void updateLesson(Long id, LessonRequestDTO lesson);

    Page<Lesson> findByName(String name, Pageable pageable);

    Page<Lesson> findByTopicId(Long topicId, Pageable pageable);

    Page<Lesson> findByOrderIndex(int orderIndex, Pageable pageable);

    Page<Lesson> findByTotalQuestion(int totalQuestion, Pageable pageable);


}
