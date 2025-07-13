package com.englishacademy.service.impl;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.entity.Topic;
import com.englishacademy.mapper.TopicMapper;
import com.englishacademy.repository.TopicRepository;
import com.englishacademy.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private TopicMapper topicMapper;

    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic getTopicById(Long id) {
        return topicRepository.findById(id).get();
    }

    @Override
    public void createTopic(TopicRequestDTO topicRequestDTO) {
        topicRepository.save(topicMapper.toEntity(topicRequestDTO));
    }

    @Override
    public void updateTopic(Long id, TopicRequestDTO topic) {
        Topic oldTopic = topicRepository.findById(id).get();
        topicMapper.updateEntityFromDto(topic, oldTopic);
        topicRepository.save(oldTopic);
    }

    @Override
    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public void deleteTopics(List<Long> ids) {
        List<Topic> topics = topicRepository.findAllById(ids);
        topicRepository.deleteAll(topics);
    }

    @Override
    public List<Topic> findByName(String name) {
        return topicRepository.findByName(name);
    }
}
