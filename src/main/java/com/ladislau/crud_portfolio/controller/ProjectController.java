package com.ladislau.crud_portfolio.controller;

import com.ladislau.crud_portfolio.dto.ProjectDTO;
import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.model.User;
import com.ladislau.crud_portfolio.repository.ProjectRepository;
import com.ladislau.crud_portfolio.repository.UserRepository;
import com.ladislau.crud_portfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> dtos = service.findAll().stream()
                .map(ProjectDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }
        List<ProjectDTO> dtos = projectRepository.findByUserId(userId).stream()
                .map(ProjectDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable Long id) {
        Project project = service.findById(id);
        if (project == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProjectDTO.fromEntity(project));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDTO dto) {
        if (dto.getUserId() == null) return ResponseEntity.badRequest().body("Informe o ID do usuário.");

        User user = userRepository.findById(dto.getUserId()).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("Usuário não encontrado.");

        Project project = ProjectDTO.toEntity(dto);
        project.setUser(user);

        Project saved = service.save(project);
        return ResponseEntity.ok(ProjectDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        Project existing = service.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setLink(dto.getLink());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId())
                    .ifPresent(existing::setUser);
        }

        Project updated = service.save(existing);
        return ResponseEntity.ok(ProjectDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Project existing = service.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
