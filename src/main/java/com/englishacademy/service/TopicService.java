package com.englishacademy.service;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.dto.response.TopicResponseDTO;
import com.englishacademy.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface TopicService {
     Page<TopicResponseDTO> getAllTopics(Pageable pageable);

     TopicResponseDTO getTopicById(Long id);

     TopicResponseDTO createTopic(TopicRequestDTO topic);

     TopicResponseDTO updateTopic(Long id, TopicRequestDTO topic);

     void deleteTopicById(Long id);

     void deleteTopics(List<Long> ids);

     Page<TopicResponseDTO> findByName(String name, Pageable pageable);
}
