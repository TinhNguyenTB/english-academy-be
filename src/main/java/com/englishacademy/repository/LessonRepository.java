package com.englishacademy.repository;

import com.englishacademy.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByName(String name);

    List<Lesson> findByTopicId(Long topicId);

    List<Lesson> findByOrderIndex(int orderIndex);

    List<Lesson> findByTotalQuestion(int totalQuestion);
}
