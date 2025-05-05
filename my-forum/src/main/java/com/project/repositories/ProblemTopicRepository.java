package com.project.repositories;

import com.project.entities.ProblemTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTopicRepository extends JpaRepository<ProblemTopic, Long> {
}
