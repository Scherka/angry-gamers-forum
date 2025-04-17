package com.project.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User();
        user.setId(1L);
        user.setNickname("testUser");
        user.setEmail("test@example.com");
        user.setMdDateCreated(LocalDateTime.now());

        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        user.setRole(role);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getNickname());
        assertEquals("test@example.com", user.getEmail());
        assertEquals(role, user.getRole());
        assertNotNull(user.getMdDateCreated());
    }

    @Test
    void testUserEquality() {
        LocalDateTime now = LocalDateTime.now();
        
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        User user1 = new User();
        user1.setId(1L);
        user1.setNickname("testUser");
        user1.setEmail("test@example.com");
        user1.setRole(role);
        user1.setMdDateCreated(now);

        User user2 = new User();
        user2.setId(1L);
        user2.setNickname("testUser");
        user2.setEmail("test@example.com");
        user2.setRole(role);
        user2.setMdDateCreated(now);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testUserInequality() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("ROLE_ADMIN");

        User user1 = new User();
        user1.setId(1L);
        user1.setNickname("user1");
        user1.setEmail("user1@example.com");
        user1.setRole(role1);
        user1.setMdDateCreated(LocalDateTime.now());

        User user2 = new User();
        user2.setId(2L);
        user2.setNickname("user2");
        user2.setEmail("user2@example.com");
        user2.setRole(role2);
        user2.setMdDateCreated(LocalDateTime.now());

        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
} 