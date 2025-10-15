package com.ladislau.crud_portfolio.repository;

import com.ladislau.crud_portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
