package com.englishacademy.service.impl;

import com.englishacademy.dto.request.LessonProgressRequest;
import com.englishacademy.dto.response.LessonProgressResponse;
import com.englishacademy.entity.LessonProgress;
import com.englishacademy.exception.BadRequestException;
import com.englishacademy.mapper.LessonProgressMapper;
import com.englishacademy.repository.LessonProgressRepository;
import com.englishacademy.repository.LessonRepository;
import com.englishacademy.repository.UserRepository;
import com.englishacademy.service.LessonProgressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LessonProgressServiceImpl implements LessonProgressService {
    LessonProgressRepository lessonProgressRepository;
    LessonProgressMapper lessonProgressMapper;
    UserRepository userRepository;
    LessonRepository lessonRepository;

    @Override
    public LessonProgressResponse create(LessonProgressRequest request) {
        Long userId = request.getUserId();
        Long lessonId = request.getLessonId();

        if (!userRepository.existsById(userId)) {
            throw new BadRequestException("User not found with ID: " + userId);
        }
        if (!lessonRepository.existsById(lessonId)) {
            throw new BadRequestException("Lesson not found with ID: " + lessonId);
        }

        boolean exists = lessonProgressRepository.existsByUserIdAndLessonId(userId, lessonId);
        if (exists) {
            throw new BadRequestException("Lesson progress already exists for user and lesson");
        }

        LessonProgress lessonProgress = lessonProgressMapper.toEntity(request);
        LessonProgress entity = lessonProgressRepository.save(lessonProgress);
        return lessonProgressMapper.toResponse(entity);
    }

    @Override
    public LessonProgressResponse update(Long id, LessonProgressRequest request) {
        LessonProgress lessonProgress = lessonProgressRepository
                .findById(id).orElseThrow(() -> new BadRequestException("Lesson progress not found with ID: " + id));

        lessonProgressMapper.updateEntity(lessonProgress, request);
        LessonProgress entity = lessonProgressRepository.save(lessonProgress);

        return lessonProgressMapper.toResponse(entity);
    }

    @Override
    public Page<LessonProgressResponse> getAll(Pageable pageable) {
        return lessonProgressRepository.findAll(pageable)
                .map(lessonProgressMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        LessonProgress lessonProgress = lessonProgressRepository
                .findById(id).orElseThrow(() -> new BadRequestException("Lesson progress not found with ID: " + id));
        lessonProgressRepository.deleteById(id);
    }
}
