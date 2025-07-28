package com.englishacademy.service;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface TopicService {
     Page<Topic> getAllTopics(Pageable pageable);

     Topic getTopicById(Long id);

     void createTopic(TopicRequestDTO topic);

     void updateTopic(Long id, TopicRequestDTO topic);

     void deleteTopicById(Long id);

     void deleteTopics(List<Long> ids);

     Page<Topic> findByName(String name, Pageable pageable);
}
