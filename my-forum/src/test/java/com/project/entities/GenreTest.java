package com.project.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    @Test
    void testGenreCreation() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test Genre");

        assertNotNull(genre);
        assertEquals(1L, genre.getId());
        assertEquals("Test Genre", genre.getName());
    }

    @Test
    void testGenreEquality() {
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Test Genre");

        Genre genre2 = new Genre();
        genre2.setId(1L);
        genre2.setName("Test Genre");

        assertEquals(genre1, genre2);
        assertEquals(genre1.hashCode(), genre2.hashCode());
    }

    @Test
    void testGenreInequality() {
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Genre 1");

        Genre genre2 = new Genre();
        genre2.setId(2L);
        genre2.setName("Genre 2");

        assertNotEquals(genre1, genre2);
        assertNotEquals(genre1.hashCode(), genre2.hashCode());
    }
} 