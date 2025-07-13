package com.englishacademy.service;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TopicService {
     List<Topic> getAllTopics();

     Topic getTopicById(Long id);

     void createTopic(TopicRequestDTO topic);

     void updateTopic(Long id, TopicRequestDTO topic);

     void deleteTopicById(Long id);

     void deleteTopics(List<Long> ids);

     List<Topic> findByName(String name);
}
