package com.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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

    @Override
    public String toString(){
        return "Thread{" +
                " id=" + this.id +
                " name=" + this.name +
                " author=" + this.author.getNickname() +
                " game=" + this.game.getName() +
                " problemTopic=" + this.problemTopic.getName() +
                " mdDateCreated=" + this.mdDateCreated;
    }
}
