package com.ladislau.crud_portfolio.controller;

import com.ladislau.crud_portfolio.dto.ProjectDTO;
import com.ladislau.crud_portfolio.dto.UserDTO;
import com.ladislau.crud_portfolio.model.User;
import com.ladislau.crud_portfolio.repository.UserRepository;
import com.ladislau.crud_portfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> dtos = userService.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        User saved = userService.save(user);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        User existing = userService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        User updated = userService.save(existing);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User existing = userService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UserDTO toDTO(User user) {
        List<ProjectDTO> projects = user.getProjects() != null ?
                user.getProjects().stream()
                        .map(ProjectDTO::fromEntity)
                        .collect(Collectors.toList())
                : List.of();
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), projects);
    }
}
