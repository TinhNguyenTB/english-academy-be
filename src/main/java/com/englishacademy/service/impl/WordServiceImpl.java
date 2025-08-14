package com.englishacademy.service.impl;

import com.englishacademy.dto.request.WordRequest;
import com.englishacademy.dto.response.WordResponse;
import com.englishacademy.entity.Word;
import com.englishacademy.exception.BadRequestException;
import com.englishacademy.mapper.WordMapper;
import com.englishacademy.repository.TopicRepository;
import com.englishacademy.repository.WordRepository;
import com.englishacademy.service.WordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
    WordRepository wordRepository;
    TopicRepository topicRepository;
    WordMapper wordMapper;

    @Override
    public WordResponse create(WordRequest request) {
        if (!topicRepository.existsById(request.getTopicId())) {
            throw new BadRequestException("Topic not found with id:" + request.getTopicId());
        }
        Word word = wordMapper.toEntity(request);
        Word wordSaved = wordRepository.save(word);
        return wordMapper.toResponse(wordSaved);
    }

    @Override
    public WordResponse update(Long id, WordRequest request) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Word not found with id" + id));
        wordMapper.updateEntity(word, request);
        Word entity = wordRepository.save(word);
        return wordMapper.toResponse(entity);
    }

    @Override
    public Page<WordResponse> getAll(Pageable pageable) {
        return wordRepository.findAll(pageable).map(wordMapper::toResponse);
    }

    @Override
    public WordResponse getById(Long id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Word not found with id" + id));
        return wordMapper.toResponse(word);
    }

    @Override
    public void delete(Long id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Word not found with id" + id));
        wordRepository.deleteById(id);
    }
}
