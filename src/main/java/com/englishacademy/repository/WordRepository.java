package com.englishacademy.repository;

import com.englishacademy.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
}
