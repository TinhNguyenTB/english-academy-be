package com.englishacademy.repository;

import com.englishacademy.entity.UserFlashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFlashcardRepository extends JpaRepository<UserFlashcard,Long> {
    boolean existsByUserIdAndWordId(Long userId, Long wordId);
}
