package com.englishacademy.repository;

import com.englishacademy.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Page<Lesson> findByName(String name, Pageable pageable);

    Page<Lesson> findByTopicId(Long topicId, Pageable pageable);

    Page<Lesson> findByOrderIndex(int orderIndex, Pageable pageable);

    Page<Lesson> findByTotalQuestion(int totalQuestion, Pageable pageable);
}
