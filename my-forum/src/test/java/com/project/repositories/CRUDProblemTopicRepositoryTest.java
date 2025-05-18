package com.project.repositories;

import com.project.entities.ProblemTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CRUDProblemTopicRepositoryTest extends AbstractRepositoryTest {

    private ProblemTopic savedProblemTopic;

    @BeforeEach
    void setUp() {
        ProblemTopic pt = new ProblemTopic();
        pt.setName("Test Problem Topic");
        savedProblemTopic = problemTopicRepository.save(pt);
    }

    @Test
    void createProblemTopic() {
        assertThat(savedProblemTopic.getId()).isNotNull();
        assertThat(savedProblemTopic.getName()).isEqualTo("Test Problem Topic");
    }

    @Test
    void readProblemTopic() {
        Optional<ProblemTopic> found = problemTopicRepository.findById(savedProblemTopic.getId());
        assertThat(found)
                .isPresent()
                .hasValueSatisfying(pt ->
                        assertThat(pt.getName()).isEqualTo("Test Problem Topic")
                );
    }

    @Test
    void updateProblemTopic() {
        savedProblemTopic.setName("Updated Topic");
        ProblemTopic updated = problemTopicRepository.save(savedProblemTopic);

        assertThat(problemTopicRepository.findById(updated.getId()))
                .isPresent()
                .get()
                .extracting(ProblemTopic::getName)
                .isEqualTo("Updated Topic");
    }

    @Test
    void deleteProblemTopic() {
        problemTopicRepository.delete(savedProblemTopic);
        assertThat(problemTopicRepository.findById(savedProblemTopic.getId())).isEmpty();
    }
}