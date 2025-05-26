package com.project.services;

import com.project.entities.Post;
import com.project.entities.Thread;
import com.project.entities.User;
import com.project.repositories.PostRepository;
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
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserService userService;

    @Mock
    private ThreadService threadService;

    @InjectMocks
    private PostService postService;

    private Post post;
    private User author;
    private Thread thread;

    @BeforeEach
    void setUp() {
        author = new User().setId(1L);
        thread = new Thread().setId(1L);

        post = new Post()
                .setId(1L)
                .setText("This is a bug.")
                .setAuthor(author)
                .setThread(thread)
                .setMdDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreatePost_success() {
        when(userService.getUserById(1L)).thenReturn(author);
        when(threadService.getById(1L)).thenReturn(thread);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post created = postService.createPost(post);

        assertNotNull(created);
        assertEquals("This is a bug.", created.getText());
    }

    @Test
    void testGetPostsByThread_returnsList() {
        when(threadService.getById(1L)).thenReturn(thread);
        when(postRepository.findByThread(thread)).thenReturn(List.of(post));

        List<Post> result = postService.getPostsByThread(1L);

        assertFalse(result.isEmpty());
        assertEquals("This is a bug.", result.get(0).getText());
    }
}