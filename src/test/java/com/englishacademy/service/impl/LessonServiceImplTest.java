package com.englishacademy.service.impl;

import com.englishacademy.dto.response.LessonResponeDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.mapper.LessonMapper;
import com.englishacademy.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LessonServiceImplTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private LessonMapper lessonMapper;
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
}
