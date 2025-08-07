package com.englishacademy.service;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.dto.response.LessonResponeDTO;
import com.englishacademy.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {

    LessonResponeDTO getLessonById(Long id);

    Page<LessonResponeDTO> getAllLessons(Pageable pageable);

    LessonResponeDTO createLesson(LessonRequestDTO lesson);

    void deleteLesson(Long id);

    LessonResponeDTO updateLesson(Long id, LessonRequestDTO lesson);

    Page<LessonResponeDTO> findByName(String name, Pageable pageable);

    Page<LessonResponeDTO> findByTopicId(Long topicId, Pageable pageable);

    Page<LessonResponeDTO> findByOrderIndex(int orderIndex, Pageable pageable);

    Page<LessonResponeDTO> findByTotalQuestion(int totalQuestion, Pageable pageable);


}
