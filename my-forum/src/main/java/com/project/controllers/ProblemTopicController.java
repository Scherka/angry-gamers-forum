package com.project.controllers;

import com.project.dto.ProblemTopicDto;
import com.project.entities.ProblemTopic;
import com.project.services.ProblemTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/problem-topics")
@RequiredArgsConstructor
public class ProblemTopicController {

    private final ProblemTopicService problemTopicService;

    @GetMapping("/{id}")
    public ResponseEntity<ProblemTopicDto> getProblemTopicById(@PathVariable Long id) {
        ProblemTopic topic = problemTopicService.getProblemTopicById(id);
        return ResponseEntity.ok(mapToDto(topic));
    }

    @GetMapping
    public ResponseEntity<List<ProblemTopicDto>> getAllProblemTopics() {
        List<ProblemTopicDto> dtoList = problemTopicService.getAllProblemTopics()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<ProblemTopicDto> createProblemTopic(@RequestBody ProblemTopicDto dto) {
        ProblemTopic topic = mapToEntity(dto);
        ProblemTopic saved = problemTopicService.createProblemTopic(topic);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProblemTopicDto> updateProblemTopic(@PathVariable Long id, @RequestBody ProblemTopicDto dto) {
        ProblemTopic topic = mapToEntity(dto);
        ProblemTopic updated = problemTopicService.updateProblemTopic(id, topic);
        return ResponseEntity.ok(mapToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblemTopic(@PathVariable Long id) {
        problemTopicService.deleteProblemTopic(id);
        return ResponseEntity.noContent().build();
    }

    private ProblemTopicDto mapToDto(ProblemTopic topic) {
        return new ProblemTopicDto(topic.getId(), topic.getName());
    }

    private ProblemTopic mapToEntity(ProblemTopicDto dto) {
        ProblemTopic topic = new ProblemTopic();
        topic.setId(dto.getId());
        topic.setName(dto.getName());
        return topic;
    }
}
