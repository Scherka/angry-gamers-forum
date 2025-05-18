package com.project.repositories;

import com.project.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CRUDRoleRepositoryTest extends AbstractRepositoryTest{

    private Role savedRole;

    @BeforeEach
    void setUp(){
        Role role = new Role();
        role.setName("TEST_ROLE");
        savedRole = roleRepository.save(role);
    }

    @AfterEach
    void tearDown(){
        roleRepository.delete(savedRole);
    }

    @Test
    void createRole() {
        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getName()).isEqualTo("TEST_ROLE");
    }

    @Test
    void readRole() {
        Optional<Role> foundRole = roleRepository.findById(savedRole.getId());
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo("TEST_ROLE");
    }

    @Test
    void updateRole() {
        savedRole.setName("UPDATED_ROLE");
        Role updatedRole = roleRepository.save(savedRole);

        assertThat(updatedRole.getName()).isEqualTo("UPDATED_ROLE");
    }

    @Test
    void deleteRole() {
        roleRepository.delete(savedRole);
        assertThat(roleRepository.findById(savedRole.getId())).isEmpty();
    }
}
