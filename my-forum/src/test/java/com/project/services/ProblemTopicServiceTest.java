package com.project.services;

import com.project.entities.ProblemTopic;
import com.project.repositories.ProblemTopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProblemTopicServiceTest {

    @Mock
    private ProblemTopicRepository problemTopicRepository;

    @InjectMocks
    private ProblemTopicService problemTopicService;

    private ProblemTopic topic;

    @BeforeEach
    void setUp() {
        topic = new ProblemTopic().setId(1L).setName("Graphics");
    }

    @Test
    void testCreateTopic_success() {
        when(problemTopicRepository.existsByName("Graphics")).thenReturn(false);
        when(problemTopicRepository.save(any(ProblemTopic.class))).thenReturn(topic);

        ProblemTopic created = problemTopicService.createProblemTopic(topic);

        assertNotNull(created);
        assertEquals("Graphics", created.getName());
    }

    @Test
    void testCreateTopic_topicExists() {
        when(problemTopicRepository.existsByName("Graphics")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> problemTopicService.createProblemTopic(topic));
    }
}