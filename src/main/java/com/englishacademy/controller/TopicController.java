package com.englishacademy.controller;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.entity.Topic;
import com.englishacademy.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/get-all")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/get/{id}")
    public Topic getTopicById(@PathVariable Long id) {
        return topicService.getTopicById(id);
    }

    @PostMapping("/create")
    public void createTopic(@Valid  @RequestBody TopicRequestDTO topicRequestDTO) {
        topicService.createTopic(topicRequestDTO);
    }

    @PutMapping("/update/{id}")
    public void updateTopic(@PathVariable Long id, @Valid @RequestBody TopicRequestDTO topicRequestDTO) {
        topicService.updateTopic(id, topicRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTopicById(@PathVariable Long id) {
        topicService.deleteTopicById(id);
    }

    @DeleteMapping("/delete")
    public void deleteTopics(@RequestBody List<Long> ids) {
        topicService.deleteTopics(ids);
    }

    @GetMapping("/find-by-name")
    public List<Topic> findByName(@RequestParam String name) {
        return topicService.findByName(name);
    }

}
