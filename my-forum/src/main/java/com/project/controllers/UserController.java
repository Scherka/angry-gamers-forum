package com.project.controllers;

import com.project.dto.UserDto;
import com.project.entities.User;
import com.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(mapToDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(mapToEntity(userDto));
        return ResponseEntity.ok(mapToDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, mapToEntity(userDto));
        return ResponseEntity.ok(mapToDto(user));
    }

    @PatchMapping("/{id}/assign-role")
    public ResponseEntity<UserDto> assignAdminRole(@PathVariable Long id) {
        User user = userService.assignRole(id);
        return ResponseEntity.ok(mapToDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getRole().getId(),
                user.getRole().getName(),
                user.getMdDateCreated()
        );
    }

    private User mapToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setMdDateCreated(dto.getMdDateCreated());
        // Привязку роли по ID делайте в сервисе (через roleService.getById(dto.getRoleId()))
        return user;
    }
}
