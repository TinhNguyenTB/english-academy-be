package com.englishacademy.service.impl;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.dto.response.TopicResponseDTO;
import com.englishacademy.entity.Topic;
import com.englishacademy.mapper.TopicMapper;
import com.englishacademy.repository.TopicRepository;
import com.englishacademy.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private TopicMapper topicMapper;

    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    @Override
    public Page<TopicResponseDTO> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable).map(topicMapper::toResponseDTO);
    }

    @Cacheable(value="TOPIC_CACHE", key = "#id")
    @Override
    public TopicResponseDTO getTopicById(Long id) {
        log.info("chay vao DB query!");
        Topic topic = topicRepository.findById(id).get();
        return topicMapper.toResponseDTO(topic);
    }

    @CachePut(value = "TOPIC_CACHE", key = "#result.id")
    @Override
    public TopicResponseDTO createTopic(TopicRequestDTO topicRequestDTO) {
        log.info("chay vao DB query!");
        Topic topic = topicRepository.save(topicMapper.toEntity(topicRequestDTO));
        return topicMapper.toResponseDTO(topic);
    }

    @CachePut(value = "TOPIC_CACHE", key = "#result.id")
    @Override
    public TopicResponseDTO updateTopic(Long id, TopicRequestDTO topic) {
        log.info("chay vao DB query!");
        Topic oldTopic = topicRepository.findById(id).get();
        topicMapper.updateEntityFromDto(topic, oldTopic);
        Topic topicSave = topicRepository.save(oldTopic);
        return topicMapper.toResponseDTO(topicSave);
    }

    @CacheEvict(value="TOPIC_CACHE", key="#id")
    @Override
    public void deleteTopicById(Long id) {
        log.info("chay vao DB query!");
        topicRepository.deleteById(id);
    }

    @Override
    public void deleteTopics(List<Long> ids) {
        List<Topic> topics = topicRepository.findAllById(ids);
        topicRepository.deleteAll(topics);
    }

    @Override
    public Page<TopicResponseDTO> findByName(String name, Pageable pageable) {
        Page<Topic> topics;
        if(!name.isBlank()){
            topics = topicRepository.findByNameContainsIgnoreCase(name, pageable);
        }else{
        topics = topicRepository.findAll(pageable);
        }
        return topics.map(topicMapper::toResponseDTO);
    }

}
