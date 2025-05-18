package com.project.repositories;

import com.project.entities.ProblemTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemTopicRepository extends JpaRepository<ProblemTopic, Long> {
}
