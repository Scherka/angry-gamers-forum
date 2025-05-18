package com.project.repositories;

import com.project.entities.*;
import com.project.entities.Thread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CRUDUserRepositoryTest extends AbstractRepositoryTest {

    private User savedUser;

    @BeforeEach
    void setUp() {
        // Создание роли
        Role userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);

        // Создание пользователя
        User user = new User();
        user.setNickname("testUser");
        user.setEmail("test@example.com");
        user.setRole(userRole);
        user.setMdDateCreated(LocalDateTime.now());
        savedUser = userRepository.save(user);
    }

    @Test
    void createUser() {
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getNickname()).isEqualTo("testUser");
        assertThat(savedUser.getRole().getName()).isEqualTo("USER");
    }

    @Test
    void readUser() {
        Optional<User> found = userRepository.findById(savedUser.getId());
        assertThat(found)
            .isPresent()
            .hasValueSatisfying(u -> {
                assertThat(u.getNickname()).isEqualTo("testUser");
                assertThat(u.getEmail()).isEqualTo("test@example.com");
            });
    }

    @Test
    void updateUser() {
        savedUser.setNickname("updatedUser");
        savedUser.setEmail("updated@example.com");
        User updated = userRepository.save(savedUser);

        assertThat(userRepository.findById(updated.getId()))
            .isPresent()
            .get()
            .satisfies(u -> {
                assertThat(u.getNickname()).isEqualTo("updatedUser");
                assertThat(u.getEmail()).isEqualTo("updated@example.com");
            });
    }

    @Test
    void deleteUser() {
        userRepository.delete(savedUser);
        assertThat(userRepository.findById(savedUser.getId())).isEmpty();
    }
}