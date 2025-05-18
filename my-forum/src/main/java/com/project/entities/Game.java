package com.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private Genre genre;


    @Column(length = 200, nullable = false)
    private String name;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Thread> threads;
    
    public Game() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id) && 
               genre.equals(game.genre) && 
               name.equals(game.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, genre, name);
    }
}
