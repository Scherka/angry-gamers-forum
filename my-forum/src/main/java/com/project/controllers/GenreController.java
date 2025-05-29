package com.project.controllers;

import com.project.dto.GenreDto;
import com.project.entities.Genre;
import com.project.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id) {
        Genre genre = genreService.getById(id);
        return ResponseEntity.ok(mapToDto(genre));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GenreDto> getGenreByName(@PathVariable String name) {
        Genre genre = genreService.getByName(name);
        return ResponseEntity.ok(mapToDto(genre));
    }

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        List<GenreDto> dtoList = genreService.getAllGenres()
                .stream().map(this::mapToDto).toList();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreService.createGenre(mapToEntity(genreDto));
        return ResponseEntity.ok(mapToDto(genre));
    }

    @PutMapping("/{name}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable String name, @RequestBody GenreDto genreDto) {
        Genre updated = genreService.updateGenre(name, mapToEntity(genreDto));
        return ResponseEntity.ok(mapToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    private GenreDto mapToDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    private Genre mapToEntity(GenreDto dto) {
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        return genre;
    }
}
