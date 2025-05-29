package com.project.services;

import com.project.entities.Role;
import com.project.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public @NonNull Role getById(@NonNull Long id) {
        log.info("[{}] Получение роли по ID {}", Instant.now(), id);
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Роль с ID " + id + " не найдена"));
    }

    public @NonNull Role getByName(@NonNull String name) {
        log.info("[{}] Получение роли по имени {}", Instant.now(), name);
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Роль с именем " + name + " не найдена"));
    }

    public @NonNull List<Role> getAll() {
        log.info("[{}] Получение всех ролей", Instant.now());
        return roleRepository.findAll();
    }

    public @NonNull Role save(@NonNull Role role) {
        log.info("[{}] Сохранение роли {}", Instant.now(), role.getName());
        return roleRepository.save(role);
    }

    public void deleteById(@NonNull Long id) {
        log.info("[{}] Удаление роли по ID {}", Instant.now(), id);
        roleRepository.deleteById(id);
    }
}
