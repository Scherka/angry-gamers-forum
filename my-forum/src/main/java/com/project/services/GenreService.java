package com.project.services;

import com.project.entities.Genre;
import com.project.repositories.GenreRepository;
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
public class GenreService {
    @Autowired
    private final GenreRepository genreRepository;

    public @NonNull Genre getById(@NonNull Long id) {
        log.info("[{}] Получить жанр по ID {}", Instant.now(), id);
        return genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now()  + "] Жанр не найден" + id));
    }

    public @NonNull Genre getByName(@NonNull String name) {
        log.info("[{}] Получить жанр по имени {}", Instant.now(), name);
        return genreRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now()  + "] Жанр не найден" + name));
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional
    public @NonNull Genre saveGenre(@NonNull Genre genre){
        return genreRepository.save(genre);
    }

    public @NonNull Genre createGenre(@NonNull Genre genre) {
        log.info("[{}] Создать жанр: {}", Instant.now(), genre.getName());
        if (genreRepository.existsByName(genre.getName())) {
            throw new IllegalArgumentException("Жанр с таким названием уже существует");
        }
        return saveGenre(genre);
    }

    public @NonNull Genre updateGenre(@NonNull String name, @NonNull Genre newData) {
        log.info("[{}] Обновить жанр ID {}", Instant.now(), name);
        Genre genre = getByName(name);
        
        if (genreRepository.existsByName(newData.getName())) {
            throw new IllegalArgumentException("[" + Instant.now() + "] Название жанра уже занято");
        }

        Optional.ofNullable(newData.getName()).map(genre::setName);
        return saveGenre(genre);
    }

    @Transactional
    public void deleteGenre(Long id) {
        log.info("[{}] Удалить жанр ID {}", Instant.now(), id);
        genreRepository.deleteById(id);
    }
}

