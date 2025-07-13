package com.englishacademy.repository;

import com.englishacademy.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

     List<Topic> findByName(String name);
}
