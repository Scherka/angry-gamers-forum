package com.project.repositories;

import com.project.entities.*;
import com.project.entities.Thread;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles("prod")
public class SearchThreadRepositoryTest extends AbstractRepositoryTest{

    private Thread thread1;
    private Thread thread2;

    @BeforeEach
    void setUp(){
        Role role = new Role();
        role.setName("USER");
        Role savedRole = roleRepository.save(role);

        User user = new User();
        user.setNickname("author");
        user.setEmail("author@test.com");
        user.setRole(savedRole);
        user.setMdDateCreated(LocalDateTime.now());
        User author = userRepository.save(user);

        Genre genre = new Genre();
        genre.setName("RPG");
        Genre savedGenre = genreRepository.save(genre);

        Game newGame = new Game();
        newGame.setName("Test Game");
        newGame.setGenre(savedGenre);
        Game game = gameRepository.save(newGame);

        ProblemTopic topic = new ProblemTopic();
        topic.setName("Technical");
        ProblemTopic problemTopic = problemTopicRepository.save(topic);

        // Создание Thread
        Thread newThread1 = new Thread();
        newThread1.setName("Bug #1 в моей сессии");
        newThread1.setAuthor(author);
        newThread1.setGame(game);
        newThread1.setProblemTopic(problemTopic);
        newThread1.setInitialPostId(1L);
        newThread1.setMdDateCreated(LocalDateTime.now().minusMinutes(20));
        thread1 = threadRepository.save(newThread1);

        Thread newThread2 = new Thread();
        newThread2.setAuthor(author);
        newThread2.setGame(game);
        newThread2.setProblemTopic(problemTopic);
        newThread2.setInitialPostId(2L);
        newThread2.setName("Проблемы с графикой в моей сессии");
        newThread2.setMdDateCreated(LocalDateTime.now());
        thread2 = threadRepository.save(newThread2);
    }

    @Test
    void threadSearch_sortBy_date(){
        List<Thread> found = threadRepository.searchThreads("сессия", "DATE");
        assertThat(found)
                .hasSize(2)
                .extracting(Thread::getName)
                .containsExactly("Проблемы с графикой в моей сессии", "Bug #1 в моей сессии");
    }
}
