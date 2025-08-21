package com.englishacademy.service.impl;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.dto.response.TopicResponseDTO;
import com.englishacademy.entity.Topic;
import com.englishacademy.mapper.TopicMapper;
import com.englishacademy.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;
    @Mock
    private TopicMapper topicMapper;
    @InjectMocks
    private TopicServiceImpl topicServiceImpl;

    @Test
    void testGetAllTopics() {
       Topic topic1 = new Topic();
       topic1.setId(1L);
       Topic topic2 = new Topic();
       topic2.setId(2L);

       TopicResponseDTO dto1 = new TopicResponseDTO();
       dto1.setId(1L);
       TopicResponseDTO dto2 = new TopicResponseDTO();
       dto2.setId(2L);
    }

    @Test
    void testGetTopicById() {
        Topic topic = new Topic();
        topic.setId(1L);

        TopicResponseDTO dto = new TopicResponseDTO();
        dto.setId(1L);

        when(topicRepository.findById(1L)).thenReturn(java.util.Optional.of(topic));
        when(topicMapper.toResponseDTO(topic)).thenReturn(dto);

        TopicResponseDTO result = topicServiceImpl.getTopicById(1L);

        assert result != null;
        assert result.getId().equals(1L);
        assert result.getName() == null;
    }

    @Test
    void testCreateTopic_success() {
        Topic topic = new Topic();
        topic.setId(1L);
        TopicRequestDTO requestDTO = new TopicRequestDTO();
        requestDTO.setName("New Topic");
        TopicResponseDTO responseDTO = new TopicResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("New Topic");

        when(topicMapper.toEntity(requestDTO)).thenReturn(topic);
        when(topicRepository.save(topic)).thenReturn(topic);
        when(topicMapper.toResponseDTO(topic)).thenReturn(responseDTO);

        TopicResponseDTO result = topicServiceImpl.createTopic(requestDTO);
        assert result != null;
        assert result.getId().equals(1L);
        assert result.getName().equals("New Topic");
        verify(topicRepository).save(topic);
        verify(topicMapper).toEntity(requestDTO);
        verify(topicMapper).toResponseDTO(topic);
        verify(topicRepository).save(topic);
    }

    @Test
    void testUpdateTopic() {
        Topic oldTopic = new Topic();
        oldTopic.setId(1L);

        TopicRequestDTO topicRequestDTO = new TopicRequestDTO();
        topicRequestDTO.setName("New Topic");

        TopicResponseDTO responseDTO = new TopicResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("New Topic");

        when(topicRepository.findById(1L)).thenReturn(Optional.of(oldTopic));
        when(topicRepository.save(oldTopic)).thenReturn(oldTopic);
        doNothing().when(topicMapper).updateEntityFromDto(topicRequestDTO, oldTopic);
        when(topicMapper.toResponseDTO(oldTopic)).thenReturn(responseDTO);

        TopicResponseDTO result = topicServiceImpl.updateTopic(1L, topicRequestDTO);

        assertEquals("New Topic", result.getName());

        verify(topicRepository).findById(1L);
        verify(topicMapper).updateEntityFromDto(topicRequestDTO, oldTopic);
        verify(topicRepository).save(oldTopic);
        verify(topicMapper).toResponseDTO(oldTopic);
    }

    @Test
    void testDeleteTopicById() {
        Long topicId = 1L;
        topicServiceImpl.deleteTopicById(topicId);
        verify(topicRepository, times(1)).deleteById(topicId);
    }

    @Test
    void testDeleteTopics() {
        Topic topic1 = new Topic();
        topic1.setId(1L);
        Topic topic2 = new Topic();
        topic2.setId(2L);

        when(topicRepository.findAllById(List.of(1L, 2L))).thenReturn(List.of(topic1, topic2));

        topicServiceImpl.deleteTopics(List.of(1L, 2L));

        verify(topicRepository).deleteAll(List.of(topic1, topic2));
    }

    @Test
    void testFindByName_success() {
        Topic topic1 = new Topic();
        topic1.setId(1L);
        topic1.setName("English");

        Topic topic2 = new Topic();
        topic2.setId(2L);
        topic2.setName("Math");

        Page<Topic> topics = new PageImpl<>(List.of(topic1, topic2));

        when(topicRepository.findByNameContainsIgnoreCase("Eng", null)).thenReturn(topics);

        Page<TopicResponseDTO> result = topicServiceImpl.findByName("Eng", null);

        assertEquals(2, result.getTotalElements());
        verify(topicRepository).findByNameContainsIgnoreCase("Eng", null);

    }

    @Test
    void testFindByName_empty() {

        String name = "";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Topic> emptyPage = Page.empty();

        when(topicRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<TopicResponseDTO> result = topicServiceImpl.findByName(name, pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(topicRepository).findAll(pageable);
    }

}

