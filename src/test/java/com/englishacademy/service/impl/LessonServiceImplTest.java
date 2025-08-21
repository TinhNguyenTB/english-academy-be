package com.englishacademy.service.impl;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.dto.response.LessonResponeDTO;
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
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);

        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);

        Page<Lesson> lessons = new PageImpl<>(List.of(lesson1, lesson2));

        LessonResponeDTO dto1 = new LessonResponeDTO();
        dto1.setId(1L);
        LessonResponeDTO dto2 = new LessonResponeDTO();
        dto2.setId(2L);
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
}
