package com.project.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
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

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Post post = (Post) o;
        return id.equals(post.id) && 
               author.equals(post.author) && 
               thread.equals(post.thread) && 
               text.equals(post.text) && 
               mdDateCreated.equals(post.mdDateCreated);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, author, thread, text, mdDateCreated);
    }
}
