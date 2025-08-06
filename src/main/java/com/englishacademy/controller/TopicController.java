package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.dto.response.TopicResponseDTO;
import com.englishacademy.entity.Topic;
import com.englishacademy.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topics")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/get")
    public ResponseData<Page<TopicResponseDTO>> getAllTopics(Pageable pageable) {
        Page<TopicResponseDTO> topics = topicService.getAllTopics(pageable);
        return ResponseData.<Page<TopicResponseDTO>>builder()
                .message(Translator.toLocale("topic.get.all.success"))
                .data(topics)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<TopicResponseDTO>  getTopicById(@PathVariable Long id) {
        TopicResponseDTO topic = topicService.getTopicById(id);
        return ResponseData.<TopicResponseDTO>builder()
                .message(Translator.toLocale("topic.get.topic.by.id.success"))
                .data(topic)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<TopicResponseDTO> createTopic(@Valid  @RequestBody TopicRequestDTO topicRequestDTO) {
        TopicResponseDTO topicCreate  = topicService.createTopic(topicRequestDTO);
        return ResponseData.<TopicResponseDTO>builder()
                .message(Translator.toLocale("topic.create.success"))
                .code(HttpStatus.CREATED.value())
                .data(topicCreate)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<TopicResponseDTO> updateTopic(@PathVariable Long id, @Valid @RequestBody TopicRequestDTO topicRequestDTO) {
        TopicResponseDTO topicUpdate = topicService.updateTopic(id, topicRequestDTO);
        return ResponseData.<TopicResponseDTO>builder()
                .message(Translator.toLocale("topic.update.success"))
                .code(HttpStatus.OK.value())
                .data(topicUpdate)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteTopicById(@PathVariable Long id) {
        topicService.deleteTopicById(id);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("topic.delete.success"))
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseData<Void> deleteTopics(@RequestBody List<Long> ids) {
        topicService.deleteTopics(ids);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("topic.delete.multiple.success"))
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @GetMapping("/find")
    public ResponseData<Page<TopicResponseDTO>> findByName(@RequestParam String name, Pageable pageable) {
        Page<TopicResponseDTO> topics = topicService.findByName(name, pageable);
        return ResponseData.<Page<TopicResponseDTO>>builder()
                .message(Translator.toLocale("topic.find.by.name.success"))
                .data(topics)
                .code(HttpStatus.OK.value())
                .build();

    }

}
