package com.project.services;

import com.project.entities.Thread;
import com.project.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThreadService {
    @Autowired
    private final ThreadRepository threadRepository;
    private final GameService gameService;
    private final ProblemTopicService problemTopicService;
    private final UserService userService;

    public @NonNull Thread getById(@NonNull Long threadId){
        log.info("[{}] Найти тред по ID: {}", Instant.now(), threadId);
        return threadRepository.findById(threadId)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Тред не найден"));
    }

    @Transactional
    public @NonNull Thread saveThread(@NonNull Thread thread){
        return threadRepository.save(thread);
    }

    public @NonNull Thread createThread(@NonNull Thread thread) {
        log.info("[{}] Создать тред: {}", Instant.now(), thread.getName());

        thread.setAuthor(userService.getUserById(thread.getAuthor().getId()));
        thread.setGame(gameService.getById(thread.getGame().getId()));
        thread.setProblemTopic(problemTopicService.getProblemTopicById(thread.getProblemTopic().getId()));
        thread.setMdDateCreated(LocalDateTime.now());

        return saveThread(thread);
    }

    public List<Thread> searchThreads(@NonNull String searchTerm, @NonNull String sortBy) {
        return threadRepository.searchThreads(searchTerm, sortBy);
    }

    public List<Thread> getThreadsByGame(@NonNull Long gameId) {
        return threadRepository.findByGame(gameService.getById(gameId));
    }

    @Transactional
    public void deleteThread(Long id) {
        log.info("[{}] Удалить тред ID {}", Instant.now(), id);
        threadRepository.deleteById(id);
    }
}