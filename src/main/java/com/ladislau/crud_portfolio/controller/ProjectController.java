package com.ladislau.crud_portfolio.controller;

import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.model.User;
import com.ladislau.crud_portfolio.repository.ProjectRepository;
import com.ladislau.crud_portfolio.repository.UserRepository;
import com.ladislau.crud_portfolio.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService service, UserRepository userRepository,
            ProjectRepository projectRepository) {
        this.service = service;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getByUser(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<Project> projects = projectRepository.findByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        Project project = service.findById(id);
        return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Project project) {
        if (project.getUser() == null || project.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("É necessário informar o ID do usuário responsável.");
        }

        User user = userRepository.findById(project.getUser().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        project.setUser(user);
        Project saved = service.save(project);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Project project) {
        Project existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setTitle(project.getTitle());
        existing.setDescription(project.getDescription());
        existing.setLink(project.getLink());

        if (project.getUser() != null && project.getUser().getId() != null) {
            userRepository.findById(project.getUser().getId())
                    .ifPresent(existing::setUser);
        }

        Project updated = service.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Project existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
