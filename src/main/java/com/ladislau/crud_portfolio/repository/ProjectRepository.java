package com.ladislau.crud_portfolio.repository;

import com.ladislau.crud_portfolio.model.Project;
import com.ladislau.crud_portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByUser(User user);


    List<Project> findByUserId(Long userId);
}
