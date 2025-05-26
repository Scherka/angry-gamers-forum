package com.project.services;

import com.project.entities.Game;
import com.project.entities.ProblemTopic;
import com.project.entities.User;
import com.project.entities.Thread;
import com.project.repositories.ThreadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ThreadServiceTest {

    @Mock
    private ThreadRepository threadRepository;

    @Mock
    private UserService userService;

    @Mock
    private GameService gameService;

    @Mock
    private ProblemTopicService problemTopicService;

    @InjectMocks
    private ThreadService threadService;

    private Thread thread;
    private User author;
    private Game game;
    private ProblemTopic topic;

    @BeforeEach
    void setUp() {
        author = new User().setId(1L);
        game = new Game().setId(1L);
        topic = new ProblemTopic().setId(1L);

        thread = new Thread()
                .setId(1L)
                .setName("Bug Report")
                .setAuthor(author)
                .setGame(game)
                .setProblemTopic(topic)
                .setMdDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreateThread_success() {
        when(userService.getUserById(1L)).thenReturn(author);
        when(gameService.getById(1L)).thenReturn(game);
        when(problemTopicService.getProblemTopicById(1L)).thenReturn(topic);
        when(threadRepository.save(any(Thread.class))).thenReturn(thread);

        Thread created = threadService.createThread(thread);

        assertNotNull(created);
        assertEquals("Bug Report", created.getName());
    }

    @Test
    void testSearchThreads_returnsList() {
        when(threadRepository.searchThreads("bug", "DATE")).thenReturn(List.of(thread));

        List<Thread> result = threadService.searchThreads("bug", "DATE");

        assertFalse(result.isEmpty());
        assertEquals("Bug Report", result.get(0).getName());
    }
}