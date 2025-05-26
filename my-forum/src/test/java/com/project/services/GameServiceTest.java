package com.project.services;

import com.project.entities.Game;
import com.project.entities.Genre;
import com.project.repositories.GameRepository;
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
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GameService gameService;

    private Game game;
    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre().setId(1L).setName("Action");
        game = new Game()
                .setId(1L)
                .setName("Cyberpunk")
                .setGenre(genre);
    }

    @Test
    void testCreateGame_success() {
        when(gameRepository.existsByName("Cyberpunk")).thenReturn(false);
        when(genreService.getByName("Action")).thenReturn(genre);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        Game created = gameService.createGame(game);

        assertNotNull(created);
        assertEquals("Cyberpunk", created.getName());
    }

    @Test
    void testCreateGame_gameExists() {
        when(gameRepository.existsByName("Cyberpunk")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> gameService.createGame(game));
    }

    @Test
    void testGetById_success() {
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Game found = gameService.getById(1L);

        assertNotNull(found);
        assertEquals("Cyberpunk", found.getName());
    }

    @Test
    void testGetById_notFound() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> gameService.getById(1L));
    }
}