package com.project.services;

import com.project.entities.Game;
import com.project.entities.Genre;
import com.project.repositories.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {
    @Autowired
    private final GameRepository gameRepository;
    private final GenreService genreService;

    public @NonNull Game getById(@NonNull Long id) {
        log.info("[{}] Получить игру по ID {}", Instant.now(), id);
        return gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Игра не найдена"));
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Transactional
    public @NonNull Game saveGame(@NonNull Game game){
        return gameRepository.save(game);
    }

    public @NonNull Game createGame(@NonNull Game game) {
        log.info("[{}] Создать игру: {}", Instant.now(), game.getName());
        if (gameRepository.existsByName(game.getName())) {
            throw new IllegalArgumentException("[" + Instant.now() + "] Игра с таким названием уже существует");
        }
        game.setGenre(genreService.getByName(game.getGenre().getName()));
        return saveGame(game);
    }

    public @NonNull Game updateGame(@NonNull Long gameId, @NonNull Game newData) {
        log.info("[{}] Обновить игру ID {}", Instant.now(), gameId);
        Game game = getById(gameId);
        
        if (gameRepository.existsByName(newData.getName())) {
            throw new IllegalArgumentException("[" + Instant.now() + "] Название игры уже занято");
        }

        Genre genre = newData.getGenre();
        if (genre != null){
            game.setGenre(genreService.getByName(genre.getName()));
        }
        Optional.ofNullable(newData.getName()).map(game::setName);

        return saveGame(game);
    }

    @Transactional
    public void deleteGame(@NonNull Long id) {
        log.info("[{}] Удалить игру ID {}", Instant.now(), id);
        gameRepository.deleteById(id);
    }
}
