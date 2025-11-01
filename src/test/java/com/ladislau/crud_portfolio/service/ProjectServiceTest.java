package com.ladislau.crud_portfolio.service;

import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.repository.ProjectRepository;
import com.ladislau.crud_portfolio.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar todos os projetos")
    void findAll_ShouldReturnAllProjects() {
        Project p1 = new Project(1l, "Site", "Descricao", "link", null);
        Project p2 = new Project(2l, "Site", "Descricao", "link", null);
        when(projectRepository.findAll()).thenReturn(Arrays.asList(p1,p2));

        List<Project> result = projectService.findAll();

        assertThat(result).hasSize(2);
        verify(projectRepository,times(1)).findAll();


    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}