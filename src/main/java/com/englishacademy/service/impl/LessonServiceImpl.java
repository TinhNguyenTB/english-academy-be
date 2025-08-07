package com.englishacademy.service.impl;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.dto.response.LessonResponeDTO;
import com.englishacademy.entity.Lesson;
import com.englishacademy.entity.Topic;
import com.englishacademy.mapper.LessonMapper;
import com.englishacademy.repository.LessonRepository;
import com.englishacademy.repository.TopicRepository;
import com.englishacademy.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true)
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;
    private LessonMapper lessonMapper;
    private TopicRepository topicRepository;

    @Cacheable(value = "LESSON_CACHE", key = "#id")
    @Override
    public LessonResponeDTO getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        return lessonMapper.toResponeDTO(lesson);
    }

    @Override
    public Page<LessonResponeDTO> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable).map(lessonMapper::toResponeDTO);
    }

    @CachePut(value = "LESSON_CACHE", key="#result.id")
    @Override
    public LessonResponeDTO createLesson(LessonRequestDTO requestDTO) {
        Lesson lesson = lessonMapper.toEntity(requestDTO);
        Topic topic = topicRepository.findById(requestDTO.getTopicId())
                .orElseThrow(()-> new RuntimeException("Topic not found"));
        lesson.setTopic(topic);
        lessonRepository.save(lesson);
        return lessonMapper.toResponeDTO(lesson);
    }

    @CacheEvict(value = "LESSON_CACHE", key="#id")
    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @CachePut(value = "LESSON_CACHE", key = "#result.id")
    @Override
    public LessonResponeDTO updateLesson(Long id, LessonRequestDTO lesson) {
        Lesson oldLesson = lessonRepository.findById(id).get();
        lessonMapper.updateEntityFromDto(lesson, oldLesson);
        lessonRepository.save(oldLesson);
        return lessonMapper.toResponeDTO(oldLesson);
    }

    @Override
    public Page<LessonResponeDTO> findByName(String name, Pageable pageable) {
        Page<Lesson> lessons;
       if(!name.isBlank()){
           lessons = lessonRepository.findByNameContainsIgnoreCase(name, pageable);
       }else{
           lessons = lessonRepository.findAll(pageable);
       }
       return lessons.map(lessonMapper::toResponeDTO);
    }

    @Override
    public Page<LessonResponeDTO> findByTopicId(Long topicId, Pageable pageable) {
        return lessonRepository.findByTopicId(topicId, pageable).map(lessonMapper::toResponeDTO);
    }

    @Override
    public Page<LessonResponeDTO> findByOrderIndex(int orderIndex, Pageable pageable) {
        return lessonRepository.findByOrderIndex(orderIndex, pageable).map(lessonMapper::toResponeDTO);
    }

    @Override
    public Page<LessonResponeDTO> findByTotalQuestion(int totalQuestion, Pageable pageable) {
        return lessonRepository.findByTotalQuestion(totalQuestion, pageable).map(lessonMapper::toResponeDTO);
    }

}
