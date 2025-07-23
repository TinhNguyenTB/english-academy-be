package com.englishacademy.repository;

import com.englishacademy.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Long> {

   Page<Topic> findByName(String name, Pageable pageable);
}
