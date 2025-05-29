package com.project.controllers;

import com.project.dto.GameDto;
import com.project.entities.Game;
import com.project.entities.Genre;
import com.project.services.GameService;
import com.project.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GenreService genreService;  // Чтобы получить жанр по id

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        Game game = gameService.getById(id);
        return ResponseEntity.ok(mapToDto(game));
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> dtoList = gameService.getAllGames()
            .stream()
            .map(this::mapToDto)
            .toList();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        Game game = mapToEntity(gameDto);
        Game saved = gameService.createGame(game);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable Long id, @RequestBody GameDto gameDto) {
        Game game = mapToEntity(gameDto);
        Game updated = gameService.updateGame(id, game);
        return ResponseEntity.ok(mapToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    private GameDto mapToDto(Game game) {
        return new GameDto(
            game.getId(),
            game.getName(),
            game.getGenre() != null ? game.getGenre().getId() : null
        );
    }

    private Game mapToEntity(GameDto dto) {
        Game game = new Game();
        game.setId(dto.getId());
        game.setName(dto.getName());

        if (dto.getGenreId() != null) {
            Genre genre = genreService.getById(dto.getGenreId());
            game.setGenre(genre);
        } else {
            game.setGenre(null);
        }

        return game;
    }
}
