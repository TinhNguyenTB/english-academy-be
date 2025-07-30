package com.englishacademy.service;


import com.englishacademy.dto.request.LessonProgressRequest;
import com.englishacademy.dto.response.LessonProgressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonProgressService {
    LessonProgressResponse create(LessonProgressRequest request);
    LessonProgressResponse update(Long id, LessonProgressRequest request);
    Page<LessonProgressResponse> getAll(Pageable pageable);
    void delete (Long id);
}
