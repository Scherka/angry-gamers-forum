package com.project.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProblemTopicTest {

    @Test
    void testProblemTopicCreation() {
        ProblemTopic topic = new ProblemTopic();
        topic.setId(1L);
        topic.setName("Test Topic");

        assertNotNull(topic);
        assertEquals(1L, topic.getId());
        assertEquals("Test Topic", topic.getName());
    }

    @Test
    void testProblemTopicEquality() {
        ProblemTopic topic1 = new ProblemTopic();
        topic1.setId(1L);
        topic1.setName("Test Topic");

        ProblemTopic topic2 = new ProblemTopic();
        topic2.setId(1L);
        topic2.setName("Test Topic");

        assertEquals(topic1, topic2);
        assertEquals(topic1.hashCode(), topic2.hashCode());
    }

    @Test
    void testProblemTopicInequality() {
        ProblemTopic topic1 = new ProblemTopic();
        topic1.setId(1L);
        topic1.setName("Topic 1");

        ProblemTopic topic2 = new ProblemTopic();
        topic2.setId(2L);
        topic2.setName("Topic 2");

        assertNotEquals(topic1, topic2);
        assertNotEquals(topic1.hashCode(), topic2.hashCode());
    }
} 