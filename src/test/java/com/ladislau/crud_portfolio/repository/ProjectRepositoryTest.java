package com.ladislau.crud_portfolio.repository;

import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    @DisplayName("Deve retornar projetos pelo ID do usuario")
    void findByUserIdCase1() {
        //Arrange cria o usuario e o projeto
        User user = new User();
        user.setName("Zoio");
        user.setEmail("Zoio@gmail.com");

        entityManager.persist(user);

        Project project = new Project();
        project.setTitle("Portfolio Angular");
        project.setDescription("Um portfoliio pessial feito em angular");
        project.setLink("https:/exemplo.com");
        project.setUser(user);

        entityManager.persist(project);
        entityManager.flush();

        //Act busca pelo ID do usuario
        List<Project> result = projectRepository.findByUserId(user.getId());

        //Assert verifica se o resultado esta correto
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getUser().getId()).isEqualTo(user.getId());
        assertThat(result.get(0).getTitle()).isEqualTo("Portfolio Angular");
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando usuario nao tem projetos")
    void findByUserIdCase2() {
        //Arrange cria o usuario e o projeto
        User user = new User();
        user.setName("Zoio");
        user.setEmail("Zoio@gmail.com");

        entityManager.persist(user);
        entityManager.flush();

        //Act busca pelo ID do usuario
        List<Project> result = projectRepository.findByUserId(user.getId());

        //Assert verifica se o resultado esta correto
        assertThat(result).isEmpty();
    }

}