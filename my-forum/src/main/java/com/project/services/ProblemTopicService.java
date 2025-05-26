package com.project.services;

import com.project.entities.ProblemTopic;
import com.project.repositories.ProblemTopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemTopicService {
    @Autowired
    private final ProblemTopicRepository problemTopicRepository;

    public @NonNull ProblemTopic getProblemTopicById(@NonNull Long id) {
        log.info("[{}] Получить тематику проблемы по ID {}", Instant.now(), id);
        return problemTopicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Тематика не найдена"));
    }

    public List<ProblemTopic> getAllProblemTopics() {
        return problemTopicRepository.findAll();
    }

    @Transactional
    public @NonNull ProblemTopic saveProblemTopic(@NonNull ProblemTopic problemTopic){
        return problemTopicRepository.save(problemTopic);
    }

    public @NonNull ProblemTopic createProblemTopic(@NonNull ProblemTopic problemTopic) {
        log.info("[{}] Создать тематику: {}", Instant.now(), problemTopic.getName());
        if (problemTopicRepository.existsByName(problemTopic.getName())) {
            throw new IllegalArgumentException("[" + Instant.now() + "Тематика с таким названием уже существует");
        }
        return saveProblemTopic(problemTopic);
    }

    public ProblemTopic updateProblemTopic(Long topicId, ProblemTopic newData) {
        log.info("[{}] Обновить тематику ID {}", Instant.now(), topicId);
        ProblemTopic topic = getProblemTopicById(topicId);
        
        if (problemTopicRepository.existsByName(newData.getName())) {
            throw new IllegalArgumentException("[" + Instant.now() + "] Название тематики уже занято");
        }

        Optional.ofNullable(newData.getName()).map(topic::setName);
        return saveProblemTopic(topic);
    }

    @Transactional
    public void deleteProblemTopic(Long id) {
        log.info("[{}] Удалить тематику ID {}", Instant.now(), id);
        problemTopicRepository.deleteById(id);
    }
}

