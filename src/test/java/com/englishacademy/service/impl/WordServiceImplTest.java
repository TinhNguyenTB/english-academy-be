package com.englishacademy.service.impl;

import com.englishacademy.dto.request.WordRequest;
import com.englishacademy.dto.response.WordResponse;
import com.englishacademy.entity.Word;
import com.englishacademy.mapper.WordMapper;
import com.englishacademy.repository.TopicRepository;
import com.englishacademy.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordServiceImplTest {
    @Mock
    private WordRepository wordRepository;
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private WordMapper wordMapper;
    @InjectMocks
    private WordServiceImpl wordServiceImpl;

    @Test
    void testCreate_success() {
        WordRequest wordRequest = new WordRequest();
        wordRequest.setTopicId(1L);
        Word word = new Word();
        WordResponse wordResponse = new WordResponse();

        when(topicRepository.existsById(1L)).thenReturn(true);
        when(wordMapper.toEntity(wordRequest)).thenReturn(word);
        when(wordRepository.save(word)).thenReturn(word);
        when(wordMapper.toResponse(word)).thenReturn(wordResponse);
        wordServiceImpl.create(wordRequest);
        verify(wordRepository).save(word);

    }

    @Test
    void testCreate_failed() {
        WordRequest wordRequest = new WordRequest();
        wordRequest.setTopicId(1L);
        when(topicRepository.existsById(1L)).thenReturn(false);
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> wordServiceImpl.create(wordRequest));
        assertEquals("Topic not found with id:1", ex.getMessage());
    }

    @Test
    void testUpdate_success() {
        WordRequest wordRequest = new WordRequest();
        wordRequest.setTopicId(1L);
        Word word = new Word();
        word.setId(1L);

        when(wordRepository.findById(1L)).thenReturn(Optional.of(word));
        when(wordRepository.save(word)).thenReturn(word);
        wordServiceImpl.update(1L, wordRequest);
        verify(wordMapper).updateEntity(word, wordRequest);
        verify(wordRepository).save(word);
    }

    @Test
    void testUpdate_failed() {
        WordRequest wordRequest = new WordRequest();
        wordRequest.setTopicId(1L);
        when(wordRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> wordServiceImpl.update(1L, wordRequest));
        assertEquals("Word not found with id1", ex.getMessage());
    }

    @Test
    void testGetAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Word> page = new PageImpl<>(Collections.emptyList());
        when(wordRepository.findAll(pageable)).thenReturn(page);

        wordServiceImpl.getAll(pageable);

        verify(wordRepository).findAll(pageable);
    }

    @Test
    void testGetById_success() {
        Word word = new Word();
        word.setId(1L);
        WordResponse wordResponse = new WordResponse();
        when(wordRepository.findById(1L)).thenReturn(Optional.of(word));
        when(wordMapper.toResponse(word)).thenReturn(wordResponse);

        WordResponse result = wordServiceImpl.getById(1L);
        assertEquals(wordResponse, result);
        verify(wordRepository).findById(1L);
    }

    @Test
    void testGetById_failed() {
        when(wordRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> wordServiceImpl.getById(1L));
        assertEquals("Word not found with id1", ex.getMessage());
    }

    @Test
    void testDelete_success() {
        Word word = new Word();
        word.setId(1L);
        when(wordRepository.findById(1L)).thenReturn(Optional.of(word));

        wordServiceImpl.delete(1L);

        verify(wordRepository).deleteById(1L);
    }

    @Test
    void testDelete_failed() {
        when(wordRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> wordServiceImpl.delete(1L));
        assertEquals("Word not found with id1", ex.getMessage());
    }

}
