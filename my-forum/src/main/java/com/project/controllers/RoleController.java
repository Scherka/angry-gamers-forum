package com.project.controllers;

import com.project.entities.Role;
import com.project.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.project.dto.RoleDto;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return ResponseEntity.ok(mapToDto(role));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDto> getRoleByName(@PathVariable String name) {
        Role role = roleService.getByName(name);
        return ResponseEntity.ok(mapToDto(role));
    }
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<Role> roles = roleService.getAll();
        return ResponseEntity.ok(mapToDtoList(roles));
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.save(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private RoleDto mapToDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
    private List<RoleDto> mapToDtoList(List<Role> roles) {
        return roles.stream()
                    .map(this::mapToDto)
                    .toList();
    }
    
}
