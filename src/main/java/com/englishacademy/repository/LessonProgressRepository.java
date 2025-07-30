package com.englishacademy.repository;

import com.englishacademy.entity.LessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
    boolean existsByUserIdAndLessonId(Long userId, Long lessonId);

}
