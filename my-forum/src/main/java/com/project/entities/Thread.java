package com.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "thread")
@Getter
@Setter
@NoArgsConstructor
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "problem_topic_id", referencedColumnName = "id", nullable = false)
    private ProblemTopic problemTopic;

    @Column(name = "initial_post_id", nullable = false)
    private Long initialPostId;

    @Column(name = "last_post_time")
    private LocalDateTime lastPostTime;

    @Column(name = "md_date_created", nullable = false)
    private LocalDateTime mdDateCreated;
}
