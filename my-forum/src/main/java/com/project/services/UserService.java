package com.project.services;

import com.project.entities.Role;
import com.project.entities.User;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private String DEFAULT_ROLE = "USER";

    public @NonNull Role getRoleByName(@NonNull String name){
        log.info("[{}] Найти роль {}", Instant.now(), name);
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Роль не найдена " + name));
    }

    public @NonNull User getUserById(@NonNull Long id){
        log.info("[{}] Найти пользователя по ID {}", Instant.now(), id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Пользователь не найден " + id));
    }

    @Transactional
    public @NonNull User saveUser(@NonNull User user){
        return userRepository.save(user);
    }

    public @NonNull User createUser(@NonNull User userToSave){
        log.info("[{}] Создать пользователя {}", Instant.now(), userToSave.getNickname());
        if (userRepository.existsByNickname(userToSave.getNickname()) ||
                userRepository.existsByEmail(userToSave.getEmail())){
            throw new IllegalArgumentException("[" + Instant.now() + "] Никнейм или почта уже заняты");
        }
        userToSave.setMdDateCreated(LocalDateTime.now());
        userToSave.setRole(getRoleByName(DEFAULT_ROLE));

        return saveUser(userToSave);
    }

    public @NonNull User assignRole(@NonNull Long userId){
        log.info("[{}] Передача прав админа пользователю {}", Instant.now(), userId);
        User user = getUserById(userId);
        if (user.getRole().equals(getRoleByName(DEFAULT_ROLE))){
            log.info("[{}] Пользователь {} уже админ", Instant.now(), user);
            return user;
        }
        user.setRole(getRoleByName("ADMIN"));
        return saveUser(user);
    }

    public @NonNull User updateUser(@NonNull Long userID, @NonNull User newData){
        log.info("[{}] Обновить пользователя по ID {}", Instant.now(), userID);
        User user = getUserById(userID);

        if (userRepository.existsByEmail(newData.getEmail()) || userRepository.existsByNickname(newData.getNickname())){
            throw new IllegalArgumentException("[" + Instant.now() + "] Никнейм или почта уже заняты");
        }

        Optional.ofNullable(newData.getEmail()).map(user::setEmail);
        Optional.ofNullable(newData.getNickname()).map(user::setNickname);

        return saveUser(user);
    }

    @Transactional
    public void deleteUser(@NonNull Long userId){
        log.info("[{}] Удалить пользователя по ID {}", Instant.now(), userId);
        userRepository.deleteById(userId);
    }
}
