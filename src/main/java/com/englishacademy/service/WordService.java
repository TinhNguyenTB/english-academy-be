package com.englishacademy.service;

import com.englishacademy.dto.request.WordRequest;
import com.englishacademy.dto.response.WordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordService {
    WordResponse create(WordRequest request);
    WordResponse update(Long id, WordRequest request);
    Page<WordResponse> getAll(Pageable pageable);
    WordResponse getById (Long id);
    void delete (Long id);
}
