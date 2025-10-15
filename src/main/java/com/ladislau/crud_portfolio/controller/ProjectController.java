package com.ladislau.crud_portfolio.controller;

import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Project> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return service.save(project);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);
        return service.save(project);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
