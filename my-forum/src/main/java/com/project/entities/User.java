package com.project.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Column(name = "md_date_created", nullable = false)
    private LocalDateTime mdDateCreated;

    // Добавляем связь с Thread (один пользователь может быть автором нескольких обсуждений)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Thread> threads;

    // Добавляем связь с Post (один пользователь может быть автором нескольких сообщений)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        User user = (User) o;
        return id.equals(user.id) && 
               nickname.equals(user.nickname) && 
               email.equals(user.email) && 
               role.equals(user.role) && 
               mdDateCreated.equals(user.mdDateCreated);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, nickname, email, role, mdDateCreated);
    }
}
