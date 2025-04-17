package com.project.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ThreadTest {

    @Test
    void testThreadCreation() {
        Thread thread = new Thread();
        thread.setId(1L);
        thread.setName("Test Thread");
        thread.setInitialPostId(1L);
        thread.setLastPostTime(LocalDateTime.now());
        thread.setMdDateCreated(LocalDateTime.now());

        User author = new User();
        author.setId(1L);
        author.setNickname("testUser");
        author.setEmail("test@example.com");
        author.setMdDateCreated(LocalDateTime.now());
        thread.setAuthor(author);

        Game game = new Game();
        game.setId(1L);
        game.setName("Test Game");
        thread.setGame(game);

        ProblemTopic problemTopic = new ProblemTopic();
        problemTopic.setId(1L);
        problemTopic.setName("Test Topic");
        thread.setProblemTopic(problemTopic);

        assertNotNull(thread);
        assertEquals(1L, thread.getId());
        assertEquals("Test Thread", thread.getName());
        assertEquals(author, thread.getAuthor());
        assertEquals(game, thread.getGame());
        assertEquals(problemTopic, thread.getProblemTopic());
        assertEquals(1L, thread.getInitialPostId());
        assertNotNull(thread.getLastPostTime());
        assertNotNull(thread.getMdDateCreated());
    }

    @Test
    void testThreadEquality() {
        LocalDateTime now = LocalDateTime.now();
        
        User author = new User();
        author.setId(1L);
        author.setNickname("testUser");
        author.setEmail("test@example.com");
        author.setMdDateCreated(now);

        Game game = new Game();
        game.setId(1L);
        game.setName("Test Game");

        ProblemTopic problemTopic = new ProblemTopic();
        problemTopic.setId(1L);
        problemTopic.setName("Test Topic");

        Thread thread1 = new Thread();
        thread1.setId(1L);
        thread1.setName("Test Thread");
        thread1.setAuthor(author);
        thread1.setGame(game);
        thread1.setProblemTopic(problemTopic);
        thread1.setInitialPostId(1L);
        thread1.setLastPostTime(now);
        thread1.setMdDateCreated(now);

        Thread thread2 = new Thread();
        thread2.setId(1L);
        thread2.setName("Test Thread");
        thread2.setAuthor(author);
        thread2.setGame(game);
        thread2.setProblemTopic(problemTopic);
        thread2.setInitialPostId(1L);
        thread2.setLastPostTime(now);
        thread2.setMdDateCreated(now);

        assertEquals(thread1, thread2);
        assertEquals(thread1.hashCode(), thread2.hashCode());
    }

    @Test
    void testThreadInequality() {
        User author1 = new User();
        author1.setId(1L);
        author1.setNickname("user1");
        author1.setEmail("user1@example.com");
        author1.setMdDateCreated(LocalDateTime.now());

        User author2 = new User();
        author2.setId(2L);
        author2.setNickname("user2");
        author2.setEmail("user2@example.com");
        author2.setMdDateCreated(LocalDateTime.now());

        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("Game 1");

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("Game 2");

        ProblemTopic topic1 = new ProblemTopic();
        topic1.setId(1L);
        topic1.setName("Topic 1");

        ProblemTopic topic2 = new ProblemTopic();
        topic2.setId(2L);
        topic2.setName("Topic 2");

        Thread thread1 = new Thread();
        thread1.setId(1L);
        thread1.setName("Thread 1");
        thread1.setAuthor(author1);
        thread1.setGame(game1);
        thread1.setProblemTopic(topic1);
        thread1.setInitialPostId(1L);
        thread1.setLastPostTime(LocalDateTime.now());
        thread1.setMdDateCreated(LocalDateTime.now());

        Thread thread2 = new Thread();
        thread2.setId(2L);
        thread2.setName("Thread 2");
        thread2.setAuthor(author2);
        thread2.setGame(game2);
        thread2.setProblemTopic(topic2);
        thread2.setInitialPostId(2L);
        thread2.setLastPostTime(LocalDateTime.now());
        thread2.setMdDateCreated(LocalDateTime.now());

        assertNotEquals(thread1, thread2);
        assertNotEquals(thread1.hashCode(), thread2.hashCode());
    }
} 