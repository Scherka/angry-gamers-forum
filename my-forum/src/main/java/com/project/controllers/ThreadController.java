package com.project.controllers;

import com.project.dto.ThreadDto;
import com.project.entities.Game;
import com.project.entities.ProblemTopic;
import com.project.entities.Thread;
import com.project.entities.User;
import com.project.services.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
public class ThreadController {

    private final ThreadService threadService;

    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> getThreadById(@PathVariable Long id) {
        Thread thread = threadService.getById(id);
        return ResponseEntity.ok(mapToDto(thread));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ThreadDto>> searchThreads(
            @RequestParam String term,
            @RequestParam String sortBy) {
        List<ThreadDto> threadsDto = threadService.searchThreads(term, sortBy)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(threadsDto);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<ThreadDto>> getThreadsByGame(@PathVariable Long gameId) {
        List<ThreadDto> threadsDto = threadService.getThreadsByGame(gameId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(threadsDto);
    }

    @PostMapping
    public ResponseEntity<ThreadDto> createThread(@RequestBody ThreadDto threadDto) {
        Thread thread = mapToEntity(threadDto);
        Thread created = threadService.createThread(thread);
        return ResponseEntity.ok(mapToDto(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long id) {
        threadService.deleteThread(id);
        return ResponseEntity.noContent().build();
    }

    private ThreadDto mapToDto(Thread thread) {
        return new ThreadDto(
                thread.getId(),
                thread.getName(),
                thread.getAuthor().getId(),
                thread.getGame().getId(),
                thread.getProblemTopic().getId(),
                thread.getMdDateCreated()
        );
    }

    private Thread mapToEntity(ThreadDto dto) {
        Thread thread = new Thread();
        thread.setId(dto.getId());
        thread.setName(dto.getName());
        thread.setMdDateCreated(dto.getMdDateCreated());

        User author = new User();
        author.setId(dto.getAuthorId());
        thread.setAuthor(author);

        Game game = new Game();
        game.setId(dto.getGameId());
        thread.setGame(game);

        ProblemTopic problemTopic = new ProblemTopic();
        problemTopic.setId(dto.getProblemTopicId());
        thread.setProblemTopic(problemTopic);

        return thread;
    }
}
