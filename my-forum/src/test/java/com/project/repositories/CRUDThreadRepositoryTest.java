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
public class CRUDThreadRepositoryTest extends AbstractRepositoryTest {

    private User author;
    private Game game;
    private ProblemTopic problemTopic;
    private Thread savedThread;

    @BeforeEach
    void setUp() {
        // Создание зависимостей
        Role role = new Role();
        role.setName("USER");
        Role savedRole = roleRepository.save(role);

        User user = new User();
        user.setNickname("author");
        user.setEmail("author@test.com");
        user.setRole(savedRole);
        user.setMdDateCreated(LocalDateTime.now());
        author = userRepository.save(user);

        Genre genre = new Genre();
        genre.setName("RPG");
        Genre savedGenre = genreRepository.save(genre);

        Game newGame = new Game();
        newGame.setName("Test Game");
        newGame.setGenre(savedGenre);
        game = gameRepository.save(newGame);

        ProblemTopic topic = new ProblemTopic();
        topic.setName("Technical");
        problemTopic = problemTopicRepository.save(topic);

        // Создание Thread
        Thread thread = new Thread();
        thread.setName("Test Thread");
        thread.setAuthor(author);
        thread.setGame(game);
        thread.setProblemTopic(problemTopic);
        thread.setInitialPostId(1L);
        thread.setMdDateCreated(LocalDateTime.now());
        savedThread = threadRepository.save(thread);
    }

    @Test
    void createThread() {
        assertThat(savedThread.getId()).isNotNull();
        assertThat(savedThread.getName()).isEqualTo("Test Thread");
    }

    @Test
    void readThread() {
        Optional<Thread> found = threadRepository.findById(savedThread.getId());
        assertThat(found)
            .isPresent()
            .hasValueSatisfying(t -> {
                assertThat(t.getAuthor().getNickname()).isEqualTo("author");
                assertThat(t.getGame().getName()).isEqualTo("Test Game");
            });
    }

    @Test
    void updateThread() {
        savedThread.setName("Updated Thread");
        Thread updated = threadRepository.save(savedThread);
        
        assertThat(threadRepository.findById(updated.getId()))
            .isPresent()
            .get()
            .extracting(Thread::getName)
            .isEqualTo("Updated Thread");
    }

    @Test
    void deleteThread() {
        threadRepository.delete(savedThread);
        assertThat(threadRepository.findById(savedThread.getId())).isEmpty();
    }

    @Test
    void findThreadsByGame() {
        List<Thread> results = threadRepository.findByGame(game);
        assertThat(results)
            .hasSize(1)
            .extracting(Thread::getName)
            .containsExactly("Test Thread");
    }
}