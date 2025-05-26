package com.project.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void testPostCreation() {
        Post post = new Post();
        post.setId(1L);
        post.setText("Test post content");
        post.setMdDateCreated(LocalDateTime.now());

        User author = new User();
        author.setId(1L);
        author.setNickname("testUser");
        author.setEmail("test@example.com");
        author.setMdDateCreated(LocalDateTime.now());
        post.setAuthor(author);

        Thread thread = new Thread();
        thread.setId(1L);
        thread.setName("Test Thread");
        thread.setMdDateCreated(LocalDateTime.now());
        post.setThread(thread);

        assertNotNull(post);
        assertEquals(1L, post.getId());
        assertEquals("Test post content", post.getText());
        assertEquals(author, post.getAuthor());
        assertEquals(thread, post.getThread());
        assertNotNull(post.getMdDateCreated());
    }

    @Test
    void testPostEquality() {
        LocalDateTime now = LocalDateTime.now();
        
        User author = new User();
        author.setId(1L);
        author.setNickname("testUser");
        author.setEmail("test@example.com");
        author.setMdDateCreated(now);

        Thread thread = new Thread();
        thread.setId(1L);
        thread.setName("Test Thread");
        thread.setMdDateCreated(now);

        Post post1 = new Post();
        post1.setId(1L);
        post1.setText("Test post content");
        post1.setAuthor(author);
        post1.setThread(thread);
        post1.setMdDateCreated(now);

        Post post2 = new Post();
        post2.setId(1L);
        post2.setText("Test post content");
        post2.setAuthor(author);
        post2.setThread(thread);
        post2.setMdDateCreated(now);

        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());
    }

    @Test
    void testPostInequality() {
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

        Thread thread1 = new Thread();
        thread1.setId(1L);
        thread1.setName("Thread 1");
        thread1.setMdDateCreated(LocalDateTime.now());

        Thread thread2 = new Thread();
        thread2.setId(2L);
        thread2.setName("Thread 2");
        thread2.setMdDateCreated(LocalDateTime.now());

        Post post1 = new Post();
        post1.setId(1L);
        post1.setText("Post 1 content");
        post1.setAuthor(author1);
        post1.setThread(thread1);
        post1.setMdDateCreated(LocalDateTime.now());

        Post post2 = new Post();
        post2.setId(2L);
        post2.setText("Post 2 content");
        post2.setAuthor(author2);
        post2.setThread(thread2);
        post2.setMdDateCreated(LocalDateTime.now());

        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
    }
} 