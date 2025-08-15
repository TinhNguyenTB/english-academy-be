package com.englishacademy.service.impl;

import com.englishacademy.dto.request.UserFlashcardRequest;
import com.englishacademy.dto.response.UserFlashcardResponse;
import com.englishacademy.entity.UserFlashcard;
import com.englishacademy.exception.BadRequestException;
import com.englishacademy.mapper.UserFlashcardMapper;
import com.englishacademy.repository.UserFlashcardRepository;
import com.englishacademy.repository.UserRepository;
import com.englishacademy.repository.WordRepository;
import com.englishacademy.service.UserFlashcardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserFlashcardServiceImpl implements UserFlashcardService {
    UserFlashcardRepository userFlashcardRepository;
    UserFlashcardMapper userFlashcardMapper;
    UserRepository userRepository;
    WordRepository wordRepository;

    @Override
    public UserFlashcardResponse create(UserFlashcardRequest request) {
        Long userId = request.getUserId();
        Long wordId = request.getWordId();

        if (!userRepository.existsById(userId)) {
            throw new BadRequestException("User not found with id:" + userId);
        }
        if (!wordRepository.existsById(wordId)) {
            throw new BadRequestException("Word not found with id:" + wordId);
        }
        if (userFlashcardRepository.existsByUserIdAndWordId(userId, wordId)) {
            throw new BadRequestException("Flashcard already exists for user and word");
        }

        UserFlashcard userFlashcard = userFlashcardMapper.toEntity(request);
        UserFlashcard saved = userFlashcardRepository.save(userFlashcard);
        return userFlashcardMapper.toResponse(saved);
    }

    @Override
    public UserFlashcardResponse update(Long id, UserFlashcardRequest request) {
        UserFlashcard userFlashcard = userFlashcardRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("UserFlashcard not found with id:" + id));

        userFlashcardMapper.updateEntity(userFlashcard, request);
        UserFlashcard entity = userFlashcardRepository.save(userFlashcard);
        return userFlashcardMapper.toResponse(entity);
    }

    @Override
    public Page<UserFlashcardResponse> getAll(Pageable pageable) {
        return userFlashcardRepository.findAll(pageable)
                .map(userFlashcardMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        UserFlashcard userFlashcard = userFlashcardRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("UserFlashcard not found with id:" + id));
        userFlashcardRepository.deleteById(id);
    }
}
