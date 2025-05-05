package com.project.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "thread")
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

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public Thread() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ProblemTopic getProblemTopic() {
        return problemTopic;
    }

    public void setProblemTopic(ProblemTopic problemTopic) {
        this.problemTopic = problemTopic;
    }

    public Long getInitialPostId() {
        return initialPostId;
    }

    public void setInitialPostId(Long initialPostId) {
        this.initialPostId = initialPostId;
    }

    public LocalDateTime getLastPostTime() {
        return lastPostTime;
    }

    public void setLastPostTime(LocalDateTime lastPostTime) {
        this.lastPostTime = lastPostTime;
    }

    public LocalDateTime getMdDateCreated() {
        return mdDateCreated;
    }

    public void setMdDateCreated(LocalDateTime mdDateCreated) {
        this.mdDateCreated = mdDateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return id.equals(thread.id) && 
               name.equals(thread.name) && 
               author.equals(thread.author) && 
               game.equals(thread.game) && 
               problemTopic.equals(thread.problemTopic) && 
               initialPostId.equals(thread.initialPostId) && 
               lastPostTime.equals(thread.lastPostTime) && 
               mdDateCreated.equals(thread.mdDateCreated);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, author, game, problemTopic, initialPostId, lastPostTime, mdDateCreated);
    }
}
