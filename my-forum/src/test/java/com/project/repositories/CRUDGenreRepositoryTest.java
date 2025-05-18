package com.project.repositories;

import com.project.entities.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CRUDGenreRepositoryTest extends AbstractRepositoryTest {

    private Genre savedGenre;

    @BeforeEach
    void setUp() {
        Genre genre = new Genre();
        genre.setName("Test Genre");
        savedGenre = genreRepository.save(genre);
    }

    @Test
    void createGenre() {
        assertThat(savedGenre.getId()).isNotNull();
        assertThat(savedGenre.getName()).isEqualTo("Test Genre");
    }

    @Test
    void readGenre() {
        Optional<Genre> found = genreRepository.findById(savedGenre.getId());
        assertThat(found)
                .isPresent()
                .hasValueSatisfying(g ->
                        assertThat(g.getName()).isEqualTo("Test Genre")
                );
    }

    @Test
    void updateGenre() {
        savedGenre.setName("Updated Genre");
        Genre updated = genreRepository.save(savedGenre);

        assertThat(genreRepository.findById(updated.getId()))
                .isPresent()
                .get()
                .extracting(Genre::getName)
                .isEqualTo("Updated Genre");
    }

    @Test
    void deleteGenre() {
        genreRepository.delete(savedGenre);
        assertThat(genreRepository.findById(savedGenre.getId())).isEmpty();
    }
}
