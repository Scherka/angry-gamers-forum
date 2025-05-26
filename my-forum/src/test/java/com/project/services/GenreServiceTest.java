package com.project.services;

import com.project.entities.Genre;
import com.project.repositories.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre().setId(1L).setName("RPG");
    }

    @Test
    void testCreateGenre_success() {
        when(genreRepository.existsByName("RPG")).thenReturn(false);
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre created = genreService.createGenre(genre);

        assertNotNull(created);
        assertEquals("RPG", created.getName());
    }

    @Test
    void testCreateGenre_genreExists() {
        when(genreRepository.existsByName("RPG")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> genreService.createGenre(genre));
    }

    @Test
    void testGetByName_success() {
        when(genreRepository.findByName("RPG")).thenReturn(Optional.of(genre));

        Genre found = genreService.getByName("RPG");

        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testGetByName_notFound() {
        when(genreRepository.findByName("RPG")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> genreService.getByName("RPG"));
    }
}