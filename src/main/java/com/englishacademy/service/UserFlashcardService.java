package com.englishacademy.service;

import com.englishacademy.dto.request.UserFlashcardRequest;
import com.englishacademy.dto.response.UserFlashcardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFlashcardService {
    UserFlashcardResponse create(UserFlashcardRequest request);
    UserFlashcardResponse update(Long id, UserFlashcardRequest request);
    Page<UserFlashcardResponse> getAll(Pageable pageable);
    void delete (Long id);
}
