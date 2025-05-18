package com.project.repositories;

import com.project.entities.*;
import com.project.entities.Thread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CRUDPostRepositoryTest extends AbstractRepositoryTest {

    private User author;
    private Thread thread;
    private Post savedPost;

    @BeforeEach
    void setUp() {
        // Создание зависимостей
        Role role = new Role();
        role.setName("USER");
        Role savedRole = roleRepository.save(role);

        User user = new User();
        user.setNickname("postAuthor");
        user.setEmail("post@test.com");
        user.setRole(savedRole);
        user.setMdDateCreated(LocalDateTime.now());
        author = userRepository.save(user);

        Genre genre = new Genre();
        genre.setName("Strategy");
        Genre savedGenre = genreRepository.save(genre);

        Game game = new Game();
        game.setName("Post Game");
        game.setGenre(savedGenre);
        Game savedGame = gameRepository.save(game);

        ProblemTopic topic = new ProblemTopic();
        topic.setName("Bugs");
        ProblemTopic savedTopic = problemTopicRepository.save(topic);

        Thread newThread = new Thread();
        newThread.setName("Test Thread");
        newThread.setAuthor(author);
        newThread.setGame(savedGame);
        newThread.setProblemTopic(savedTopic);
        newThread.setInitialPostId(1L);
        newThread.setMdDateCreated(LocalDateTime.now());
        thread = threadRepository.save(newThread);

        // Создание Post
        Post post = new Post();
        post.setText("Test post content");
        post.setAuthor(author);
        post.setThread(thread);
        post.setMdDateCreated(LocalDateTime.now());
        savedPost = postRepository.save(post);
    }

    @Test
    void createPost() {
        assertThat(savedPost.getId()).isNotNull();
        assertThat(savedPost.getText()).isEqualTo("Test post content");
    }

    @Test
    void readPost() {
        Optional<Post> found = postRepository.findById(savedPost.getId());
        assertThat(found)
            .isPresent()
            .hasValueSatisfying(p -> {
                assertThat(p.getAuthor().getEmail()).isEqualTo("post@test.com");
                assertThat(p.getThread().getName()).isEqualTo("Test Thread");
            });
    }

    @Test
    void updatePost() {
        savedPost.setText("Updated content");
        Post updated = postRepository.save(savedPost);
        
        assertThat(postRepository.findById(updated.getId()))
            .isPresent()
            .get()
            .extracting(Post::getText)
            .isEqualTo("Updated content");
    }

    @Test
    void deletePost() {
        postRepository.delete(savedPost);
        assertThat(postRepository.findById(savedPost.getId())).isEmpty();
    }

    @Test
    void findPostsByThread() {
        List<Post> posts = postRepository.findByThread(thread);
        assertThat(posts)
            .hasSize(1)
            .extracting(Post::getText)
            .containsExactly("Test post content");
    }
}