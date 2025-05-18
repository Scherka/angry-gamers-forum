package com.project.repositories;

import com.project.entities.*;
import com.project.entities.Thread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CRUDGameRepositoryTest extends AbstractRepositoryTest {

    private Game savedGame;

    @BeforeEach
    void setUp() {
        // Создание жанра
        Genre savedGenre = new Genre();
        savedGenre.setName("RPG");
        genreRepository.save(savedGenre);

        // Создание игры
        Game game = new Game();
        game.setName("Test Game");
        game.setGenre(savedGenre);
        savedGame = gameRepository.save(game);
    }

    @Test
    void createGame() {
        assertThat(savedGame.getId()).isNotNull();
        assertThat(savedGame.getName()).isEqualTo("Test Game");
        assertThat(savedGame.getGenre().getName()).isEqualTo("RPG");
    }

    @Test
    void readGame() {
        Optional<Game> found = gameRepository.findById(savedGame.getId());
        assertThat(found)
            .isPresent()
            .hasValueSatisfying(g -> {
                assertThat(g.getName()).isEqualTo("Test Game");
                assertThat(g.getGenre()).isNotNull();
            });
    }

    @Test
    void updateGame() {
        savedGame.setName("Updated Game");
        
        Genre newGenre = new Genre();
        newGenre.setName("Action");
        genreRepository.save(newGenre);
        
        savedGame.setGenre(newGenre);
        Game updated = gameRepository.save(savedGame);

        assertThat(gameRepository.findById(updated.getId()))
            .isPresent()
            .get()
            .satisfies(g -> {
                assertThat(g.getName()).isEqualTo("Updated Game");
                assertThat(g.getGenre().getName()).isEqualTo("Action");
            });
    }

    @Test
    void deleteGame() {
        gameRepository.delete(savedGame);
        assertThat(gameRepository.findById(savedGame.getId())).isEmpty();
    }
}