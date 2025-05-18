package com.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
