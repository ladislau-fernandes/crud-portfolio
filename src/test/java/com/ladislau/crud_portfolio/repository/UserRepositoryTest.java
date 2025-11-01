package com.ladislau.crud_portfolio.repository;

import com.ladislau.crud_portfolio.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Busca o email no DB")
    void findByEmailCase1() {
        // Arrange (configura os dados)
        User user = new User();
        user.setName("Zoio");
        user.setEmail("example@email.com");

        entityManager.persist(user);
        entityManager.flush(); // força o commit no banco antes da consulta

        // Act (executa a ação)
        Optional<User> foundedUser = userRepository.findByEmail("example@email.com");

        // Assert (verifica o resultado)
        assertThat(foundedUser.isPresent()).isTrue();
        assertThat(foundedUser.get().getEmail()).isEqualTo("example@email.com");
    }

    @Test
    @DisplayName("Deve retornar TRUE quando existir um suario com email informado")
    void existsByEmailCase1() {
        String email = "zoio@email.com";
        User user = new User();
        user.setName("Zoio");
        user.setEmail(email);

        entityManager.persist(user);
        entityManager.flush();

        boolean exists = userRepository.existsByEmail(email);

        assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve retornar FALSE quando nao existir um suario com email informado")
    void existsByEmailCase2() {
        String email = "naoexiste@email.com";

        boolean exists = userRepository.existsByEmail(email);

        assertThat(exists).isFalse();

    }

}