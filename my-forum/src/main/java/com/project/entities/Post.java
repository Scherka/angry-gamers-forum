package com.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "thread_id", referencedColumnName = "id", nullable = false)
    private Thread thread;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(name = "md_date_created", nullable = false)
    private LocalDateTime mdDateCreated;
}
