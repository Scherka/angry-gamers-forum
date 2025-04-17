package com.project.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testGameCreation() {
        Game game = new Game();
        game.setId(1L);
        game.setName("Test Game");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test Genre");
        game.setGenre(genre);

        assertNotNull(game);
        assertEquals(1L, game.getId());
        assertEquals("Test Game", game.getName());
        assertEquals(genre, game.getGenre());
    }

    @Test
    void testGameEquality() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test Genre");

        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("Test Game");
        game1.setGenre(genre);

        Game game2 = new Game();
        game2.setId(1L);
        game2.setName("Test Game");
        game2.setGenre(genre);

        assertEquals(game1, game2);
        assertEquals(game1.hashCode(), game2.hashCode());
    }

    @Test
    void testGameInequality() {
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Genre 1");

        Genre genre2 = new Genre();
        genre2.setId(2L);
        genre2.setName("Genre 2");

        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("Game 1");
        game1.setGenre(genre1);

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("Game 2");
        game2.setGenre(genre2);

        assertNotEquals(game1, game2);
        assertNotEquals(game1.hashCode(), game2.hashCode());
    }
} 