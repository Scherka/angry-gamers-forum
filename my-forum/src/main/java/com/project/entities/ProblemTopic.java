package com.project.entities;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "problem_topic")
public class ProblemTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @OneToMany(mappedBy = "problemTopic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Thread> threads; // связь 1:N с Thread
   
    public ProblemTopic() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemTopic that = (ProblemTopic) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name);
    }
}
