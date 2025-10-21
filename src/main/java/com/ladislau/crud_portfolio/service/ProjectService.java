package com.ladislau.crud_portfolio.service;

import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import com.ladislau.crud_portfolio.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Project findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Projeto com ID " + id + " não encontrado."));
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public void delete(Long id) {
        Project existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto com ID " + id + " não encontrado."));
        repository.delete(existing);
    }
}
