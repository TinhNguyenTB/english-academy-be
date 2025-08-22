package com.englishacademy.service.impl;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.dto.response.LessonResponeDTO;
import com.englishacademy.dto.response.TopicResponseDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.entity.Topic;
import com.englishacademy.mapper.LessonMapper;
import com.englishacademy.repository.LessonRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LessonServiceImplTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private LessonMapper lessonMapper;
    @Mock
    private TopicRepository topicRepository;
    @InjectMocks
    private LessonServiceImpl lessonServiceImpl;

    @Test
    void testGetLessonById() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);

        LessonResponeDTO dto = new LessonResponeDTO();
        dto.setId(1L);

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        when(lessonMapper.toResponeDTO(lesson)).thenReturn(dto);

        LessonResponeDTO result = lessonServiceImpl.getLessonById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(lessonRepository).findById(1L);
        verify(lessonMapper).toResponeDTO(lesson);
    }

    @Test
    void testGetAllLessons() {
        Pageable pageable = PageRequest.of(0, 10);
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);

        Page<Lesson> lessons = new PageImpl<>(List.of(lesson1, lesson2));
        when(lessonRepository.findAll(pageable)).thenReturn(lessons);
        when(lessonMapper.toResponeDTO(lesson1)).thenReturn(new LessonResponeDTO());
        when(lessonMapper.toResponeDTO(lesson2)).thenReturn(new LessonResponeDTO());

        Page<LessonResponeDTO> result = lessonServiceImpl.getAllLessons(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(lessonRepository).findAll(pageable);
    }

    @Test
    void testCreateLesson_success() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        Topic topic = new Topic();
        topic.setId(1L);
        LessonRequestDTO lessonRequestDTO = new LessonRequestDTO();
        lessonRequestDTO.setTopicId(1L);
        LessonResponeDTO lessonResponeDTO = new LessonResponeDTO();
        lessonResponeDTO.setId(1L);

        when(lessonMapper.toEntity(lessonRequestDTO)).thenReturn(lesson);
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        when(lessonMapper.toResponeDTO(lesson)).thenReturn(lessonResponeDTO);

        LessonResponeDTO result = lessonServiceImpl.createLesson(lessonRequestDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(topicRepository).findById(1L);
        verify(lessonRepository).save(lesson);
        verify(lessonMapper).toResponeDTO(lesson);
    }

    @Test
    void testCreateLesson_failed() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);

        LessonRequestDTO requestDTO = new LessonRequestDTO();
        requestDTO.setTopicId(1L);

        when(lessonMapper.toEntity(requestDTO)).thenReturn(lesson);
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> lessonServiceImpl.createLesson(requestDTO));

        assertEquals("Topic not found", ex.getMessage());
    }

    @Test
    void testDeleteLesson() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);

        lessonServiceImpl.deleteLesson(1L);
        verify(lessonRepository).deleteById(1L);
    }

    @Test
    void testUpdateLesson() {
       Lesson oldLesson = new Lesson();
       oldLesson.setId(1L);
       LessonResponeDTO responeDTO = new LessonResponeDTO();
       responeDTO.setId(1L);
       LessonRequestDTO lessonRequestDTO = new LessonRequestDTO();
       lessonRequestDTO.setTopicId(1L);

       when(lessonRepository.findById(1L)).thenReturn(Optional.of(oldLesson));
       when(lessonMapper.toResponeDTO(oldLesson)).thenReturn(responeDTO);
       lessonServiceImpl.updateLesson(1L, lessonRequestDTO);
       verify(lessonRepository).findById(1L);
       verify(lessonMapper).toResponeDTO(oldLesson);
       verify(lessonMapper).updateEntityFromDto(lessonRequestDTO, oldLesson);
       verify(lessonRepository).save(oldLesson);
    }

    @Test
    void testFindByName() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setName("English Basics");

        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setName("Advanced English");

        Page<Lesson> lessons = new PageImpl<>(List.of(lesson1, lesson2));
        when(lessonRepository.findByNameContainsIgnoreCase("English", null)).thenReturn(lessons);

        Page<LessonResponeDTO> result = lessonServiceImpl.findByName("English", null);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(lessonRepository).findByNameContainsIgnoreCase("English", null);
        verify(lessonMapper).toResponeDTO(lesson1);
        verify(lessonMapper).toResponeDTO(lesson2);
    }

    @Test
    void testFindByName_empty() {
        String name = "";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Lesson> emptyPage = Page.empty();

        when(lessonRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<LessonResponeDTO> result = lessonServiceImpl.findByName(name, pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(lessonRepository).findAll(pageable);
    }

    @Test
    void testFindByTopicId() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setTopic(new Topic());
        lesson1.getTopic().setId(1L);

        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setTopic(new Topic());
        lesson2.getTopic().setId(1L);

        Page<Lesson> lessons = new PageImpl<>(List.of(lesson1, lesson2));
        when(lessonRepository.findByTopicId(1L, null)).thenReturn(lessons);

        Page<LessonResponeDTO> result = lessonServiceImpl.findByTopicId(1L, null);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(lessonRepository).findByTopicId(1L, null);
        verify(lessonMapper).toResponeDTO(lesson1);
        verify(lessonMapper).toResponeDTO(lesson2);
    }

    @Test
    void testFindByOrderIndex() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setOrderIndex(1);

        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setOrderIndex(1);

        Page<Lesson> lessons = new PageImpl<>(List.of(lesson1, lesson2));
        when(lessonRepository.findByOrderIndex(1, null)).thenReturn(lessons);

        Page<LessonResponeDTO> result = lessonServiceImpl.findByOrderIndex(1, null);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(lessonRepository).findByOrderIndex(1, null);
        verify(lessonMapper).toResponeDTO(lesson1);
        verify(lessonMapper).toResponeDTO(lesson2);
    }

    @Test
    void testFindByTotalQuestion() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setTotalQuestion(5);

        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setTotalQuestion(5);

        Page<Lesson> lessons = new PageImpl<>(List.of(lesson1, lesson2));
        when(lessonRepository.findByTotalQuestion(5, null)).thenReturn(lessons);

        Page<LessonResponeDTO> result = lessonServiceImpl.findByTotalQuestion(5, null);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(lessonRepository).findByTotalQuestion(5, null);
        verify(lessonMapper).toResponeDTO(lesson1);
        verify(lessonMapper).toResponeDTO(lesson2);
    }
}
