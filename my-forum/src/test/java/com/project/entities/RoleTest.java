package com.project.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleCreation() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        assertNotNull(role);
        assertEquals(1L, role.getId());
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void testRoleEquality() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("ROLE_USER");

        assertEquals(role1, role2);
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testRoleInequality() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("ROLE_ADMIN");

        assertNotEquals(role1, role2);
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testRoleNameSetterGetter() {
        Role role = new Role();
        role.setName("Moderator");

        assertEquals("Moderator", role.getName());
    }

    @Test
    void testRoleIdSetterGetter() {
        Role role = new Role();
        role.setId(42L);

        assertEquals(42L, role.getId());
    }
}
