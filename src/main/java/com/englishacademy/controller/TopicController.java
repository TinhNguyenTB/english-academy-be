package com.englishacademy.controller;

import com.englishacademy.config.locale.Translator;
import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.dto.response.ResponseData;
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
    public ResponseData<Page<Topic>> getAllTopics(Pageable pageable) {
        Page<Topic> topics = topicService.getAllTopics(pageable);
        return ResponseData.<Page<Topic>>builder()
                .message(Translator.toLocale("topic.get.all.success"))
                .data(topics)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<Topic>  getTopicById(@PathVariable Long id) {
        Topic topic = topicService.getTopicById(id);
        return ResponseData.<Topic>builder()
                .message(Translator.toLocale("topic.get.topic.by.id.success"))
                .data(topic)
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/create")
    public ResponseData<Void> createTopic(@Valid  @RequestBody TopicRequestDTO topicRequestDTO) {
        topicService.createTopic(topicRequestDTO);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("topic.create.success"))
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseData<Void> updateTopic(@PathVariable Long id, @Valid @RequestBody TopicRequestDTO topicRequestDTO) {
        topicService.updateTopic(id, topicRequestDTO);
        return ResponseData.<Void>builder()
                .message(Translator.toLocale("topic.update.success"))
                .code(HttpStatus.OK.value())
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
    public ResponseData<Page<Topic>> findByName(@RequestParam String name, Pageable pageable) {
        Page<Topic> topics = topicService.findByName(name, pageable);
        return ResponseData.<Page<Topic>>builder()
                .message(Translator.toLocale("topic.find.by.name.success"))
                .data(topics)
                .code(HttpStatus.OK.value())
                .build();

    }

}
